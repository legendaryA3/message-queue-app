package com.aprisoft.message.queue.app.services.producers.impl;

import com.aprisoft.message.queue.app.controllers.parameters.SyncRecordParameters;
import com.aprisoft.message.queue.app.utilities.kafka.TopicsConstantsUtils;
import com.aprisoft.message.queue.app.entities.DataSync;
import com.aprisoft.message.queue.app.entities.entity.views.ClinicView;
import com.aprisoft.message.queue.app.entities.enumerations.SyncStatus;
import com.aprisoft.message.queue.app.services.ClinicService;
import com.aprisoft.message.queue.app.services.DataSyncService;
import com.aprisoft.message.queue.app.services.producers.ProducerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class ClinicProducer implements ProducerImpl {

    private final KafkaTemplate<String, List<ClinicView>> clinicsKafkaTemplate;
    private final KafkaTemplate<String, ClinicView> clinicKafkaTemplate;
    private final ClinicService clinicService;
    private final DataSyncService dataSyncService;
    private static final Logger logger = LoggerFactory.getLogger(ClinicProducer.class.getSimpleName());

    public ClinicProducer(
            KafkaTemplate<String, List<ClinicView>> clinicsKafkaTemplate,
            KafkaTemplate<String, ClinicView> clinicKafkaTemplate,
            ClinicService clinicService,
            DataSyncService dataSyncService) {
        this.clinicsKafkaTemplate = clinicsKafkaTemplate;
        this.clinicKafkaTemplate = clinicKafkaTemplate;
        this.clinicService = clinicService;
        this.dataSyncService = dataSyncService;
    }

    @Override
    public void produceAndSyncData(SyncRecordParameters syncRecordParameters) {
        List<ClinicView> clinicViews;

        clinicViews = clinicService.getClinicDataByMaxTime(syncRecordParameters.getLastSyncDateTime());

        clinicViews.stream().forEach(
                clinicView -> {
                    final ProducerRecord<String, ClinicView> record = new ProducerRecord<>(TopicsConstantsUtils.CLINIC_TOPIC, clinicView.getUuid(), clinicView);
                    ListenableFuture<SendResult<String, ClinicView>> future = clinicKafkaTemplate
                            .send(record);

                    future
                            .addCallback(new KafkaSendCallback<String, ClinicView>() {
                                @Override
                                public void onFailure(KafkaProducerException e) {
                                    logger.error("Error producing clinic data -> {}", e.getMessage());
                                }

                                @Override
                                public void onSuccess(SendResult<String, ClinicView> result) {
                                    handleClinicProducerSuccess(clinicView);
                                }
                            });
                }
        );
    }

    private void handleClinicProducerSuccess(ClinicView clinicView){
        // Updating the record status
        clinicService.updateSyncStatus(clinicView.getUuid(), SyncStatus.PROCESSING.toString());

        DataSync dataSync = dataSyncService.getDataSync(UUID.fromString(clinicView.getPatientUuid()));

        if(dataSync == null){
            DataSync newDataSync = new DataSync();
            newDataSync.setPatientId(UUID.fromString(clinicView.getPatientUuid()));
            newDataSync.setPatientTableSyncDate(clinicView.getLastModified());
            newDataSync.setFacilityId(clinicView.getFacilityId());
            dataSyncService.save(newDataSync);

        }else {
            // dataSync.setPatientId(UUID.fromString(patient.getUuid()));
            dataSync.setClinicTableSyncDate(clinicView.getLastModified());
            dataSyncService.save(dataSync);
        }
    }
}
