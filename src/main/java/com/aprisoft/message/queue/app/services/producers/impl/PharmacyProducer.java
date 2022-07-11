package com.aprisoft.message.queue.app.services.producers.impl;

import com.aprisoft.message.queue.app.controllers.parameters.SyncRecordParameters;
import com.aprisoft.message.queue.app.utilities.kafka.TopicsConstantsUtils;
import com.aprisoft.message.queue.app.entities.DataSync;
import com.aprisoft.message.queue.app.entities.entity.views.PharmacyView;
import com.aprisoft.message.queue.app.entities.enumerations.SyncStatus;
import com.aprisoft.message.queue.app.services.DataSyncService;
import com.aprisoft.message.queue.app.services.PharmacyService;
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
public class PharmacyProducer implements ProducerImpl {

    private final KafkaTemplate<String, List<PharmacyView>> pharmsKafkaTemplate;
    private final KafkaTemplate<String, PharmacyView> pharmKafkaTemplate;
    private final PharmacyService pharmacyService;
    private final DataSyncService dataSyncService;
    private static final Logger logger = LoggerFactory.getLogger(PharmacyProducer.class.getSimpleName());

    public PharmacyProducer(
            KafkaTemplate<String, List<PharmacyView>> pharmsKafkaTemplate, KafkaTemplate<String, PharmacyView> pharmKafkaTemplate, PharmacyService pharmacyService, DataSyncService dataSyncService
    ) {
        this.pharmsKafkaTemplate = pharmsKafkaTemplate;
        this.pharmKafkaTemplate = pharmKafkaTemplate;
        this.pharmacyService = pharmacyService;
        this.dataSyncService = dataSyncService;
    }

    @Override
    public void produceAndSyncData(SyncRecordParameters syncRecordParameters) {
        List<PharmacyView> pharmacyViews;

        pharmacyViews = pharmacyService.getPharmacyDataByMaxTime(syncRecordParameters.getLastSyncDateTime());

        pharmacyViews.stream().forEach(
                pharmacyView -> {
                    final ProducerRecord<String, PharmacyView> record = new ProducerRecord<>(TopicsConstantsUtils.PHARMACY_TOPIC, pharmacyView.getUuid(), pharmacyView);
                    ListenableFuture<SendResult<String, PharmacyView>> future = pharmKafkaTemplate
                            .send(record);

                    future
                            .addCallback(new KafkaSendCallback<String, PharmacyView>() {
                                @Override
                                public void onFailure(KafkaProducerException e) {
                                    logger.error("Old patient -> {}", e.getMessage());
                                }

                                @Override
                                public void onSuccess(SendResult<String, PharmacyView> result) {
                                    handlePharmacyProducerSuccess(pharmacyView);
                                }
                            });
                }
        );
    }

    private void handlePharmacyProducerSuccess(PharmacyView pharmacyView){
        // Updating the record status
        pharmacyService.updateSyncStatus(pharmacyView.getUuid(), SyncStatus.PROCESSING.toString());

        DataSync dataSync = dataSyncService.getDataSync(UUID.fromString(pharmacyView.getPatientUuid()));

        if(dataSync == null){
            DataSync newDataSync = new DataSync();
            newDataSync.setPatientId(UUID.fromString(pharmacyView.getPatientUuid()));
            newDataSync.setPatientTableSyncDate(pharmacyView.getLastModified());
            newDataSync.setFacilityId(pharmacyView.getFacilityId());
            dataSyncService.save(newDataSync);

        }else {
            // dataSync.setPatientId(UUID.fromString(patient.getUuid()));
            dataSync.setPharmacyTableSyncDate(pharmacyView.getLastModified());
            dataSyncService.save(dataSync);
        }
    }
}
