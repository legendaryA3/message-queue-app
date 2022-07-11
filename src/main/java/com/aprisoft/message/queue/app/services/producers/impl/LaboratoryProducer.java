package com.aprisoft.message.queue.app.services.producers.impl;

import com.aprisoft.message.queue.app.controllers.parameters.SyncRecordParameters;
import com.aprisoft.message.queue.app.utilities.kafka.TopicsConstantsUtils;
import com.aprisoft.message.queue.app.entities.DataSync;
import com.aprisoft.message.queue.app.entities.entity.views.LaboratoryView;
import com.aprisoft.message.queue.app.entities.enumerations.SyncStatus;
import com.aprisoft.message.queue.app.services.DataSyncService;
import com.aprisoft.message.queue.app.services.LaboratoryService;
import com.aprisoft.message.queue.app.services.producers.ProducerImpl;
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

@Component
public class LaboratoryProducer implements ProducerImpl {

    private final KafkaTemplate<String, List<LaboratoryView>> labsKafkaTemplate;
    private final KafkaTemplate<String, LaboratoryView> labKafkaTemplate;
    private final LaboratoryService laboratoryService;
    private final DataSyncService dataSyncService;
    private static final Logger logger = LoggerFactory.getLogger(LaboratoryProducer.class.getSimpleName());

    public LaboratoryProducer(
            KafkaTemplate<String, List<LaboratoryView>> labsKafkaTemplate,
            KafkaTemplate<String, LaboratoryView> labKafkaTemplate,
            LaboratoryService laboratoryService,
            DataSyncService dataSyncService
    ) {
        this.labsKafkaTemplate = labsKafkaTemplate;
        this.labKafkaTemplate = labKafkaTemplate;
        this.laboratoryService = laboratoryService;
        this.dataSyncService = dataSyncService;
    }

    @Override
    public void produceAndSyncData(SyncRecordParameters syncRecordParameters) {
        List<LaboratoryView> laboratoryViews;

        laboratoryViews = laboratoryService.getLaboratoryDataByMaxTime(syncRecordParameters.getLastSyncDateTime());

        laboratoryViews.stream().forEach(
                laboratoryView -> {
                    final ProducerRecord<String, LaboratoryView> record = new ProducerRecord<>(TopicsConstantsUtils.LABORATORY_TOPIC, laboratoryView.getUuid(), laboratoryView);
                    ListenableFuture<SendResult<String, LaboratoryView>> future = labKafkaTemplate
                            .send(record);

                    future
                            .addCallback(new KafkaSendCallback<String, LaboratoryView>() {
                                @Override
                                public void onFailure(KafkaProducerException e) {
                                    logger.error("Error producing clinic data -> {}", e.getMessage());
                                }

                                @Override
                                public void onSuccess(SendResult<String, LaboratoryView> result) {
                                    handleLaboratoryProducerSuccess(laboratoryView);
                                }
                            });
                }
        );
    }

    private void handleLaboratoryProducerSuccess(LaboratoryView laboratoryView){
        // Updating the record status
        laboratoryService.updateSyncStatus(laboratoryView.getUuid(), SyncStatus.PROCESSING.toString());

        DataSync dataSync = dataSyncService.getDataSync(UUID.fromString(laboratoryView.getPatientUuid()));

        if(dataSync == null){
            DataSync newDataSync = new DataSync();
            newDataSync.setPatientId(UUID.fromString(laboratoryView.getPatientUuid()));
            newDataSync.setPatientTableSyncDate(laboratoryView.getLastModified());
            newDataSync.setFacilityId(laboratoryView.getFacilityId());
            dataSyncService.save(newDataSync);
        }else {
            // dataSync.setPatientId(UUID.fromString(patient.getUuid()));
            dataSync.setLaboratoryTableSyncDate(laboratoryView.getLastModified());
            dataSyncService.save(dataSync);
        }
    }
}
