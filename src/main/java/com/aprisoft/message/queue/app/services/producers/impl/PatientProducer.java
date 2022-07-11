package com.aprisoft.message.queue.app.services.producers.impl;


import com.aprisoft.message.queue.app.controllers.parameters.SyncRecordParameters;
import com.aprisoft.message.queue.app.utilities.kafka.TopicsConstantsUtils;
import com.aprisoft.message.queue.app.entities.DataSync;
import com.aprisoft.message.queue.app.entities.entity.views.PatientView;
import com.aprisoft.message.queue.app.entities.enumerations.SyncStatus;
import com.aprisoft.message.queue.app.services.DataSyncService;
import com.aprisoft.message.queue.app.services.PatientService;
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
public class PatientProducer implements ProducerImpl {

    private final KafkaTemplate<String, List<PatientView>> patientsKafkaTemplate;
    private final KafkaTemplate<String, PatientView> patientKafkaTemplate;
    private final PatientService patientService;
    private static final Logger logger = LoggerFactory.getLogger(PatientProducer.class.getSimpleName());

    private final DataSyncService dataSyncService;

    public PatientProducer(
            KafkaTemplate<String, List<PatientView>> patientsKafkaTemplate,
            KafkaTemplate<String, PatientView> patientKafkaTemplate,
            PatientService patientService,
            DataSyncService dataSyncService
    ) {
        this.patientsKafkaTemplate = patientsKafkaTemplate;
        this.patientKafkaTemplate = patientKafkaTemplate;
        this.patientService = patientService;
        this.dataSyncService = dataSyncService;
    }

    @Override
    public void produceAndSyncData(SyncRecordParameters syncRecordParameters) {

        List<PatientView> patients;

        patients = patientService.getPatientByMaxTime(syncRecordParameters.getLastSyncDateTime());

        patients.stream().forEach(
                patient -> {
                    final ProducerRecord<String, PatientView> record = new ProducerRecord<>(TopicsConstantsUtils.PATIENT_TOPIC, patient.getUuid(), patient);
                    ListenableFuture<SendResult<String, PatientView>> future = patientKafkaTemplate
                            .send(record);

                    future
                            .addCallback(new KafkaSendCallback<String, PatientView>() {
                                @Override
                                public void onFailure(KafkaProducerException e) {
                                    logger.error("Old patient -> {}", e.getMessage());
                                }

                                @Override
                                public void onSuccess(SendResult<String, PatientView> result) {
                                    handlePatientProducerSuccess(patient);
                                }
                            });
                }
        );
    }

    private void handlePatientProducerSuccess(PatientView patient){

        // Updating the record status
        patientService.updateSyncStatus(patient.getUuid(), SyncStatus.PROCESSING.toString());

        DataSync dataSync = dataSyncService.getDataSync(UUID.fromString(patient.getUuid()));

        if(dataSync == null){
            DataSync newDataSync = new DataSync();
            newDataSync.setPatientId(UUID.fromString(patient.getUuid()));
            newDataSync.setPatientTableSyncDate(patient.getLastModified());
            newDataSync.setFacilityId(patient.getFacilityId());
            dataSyncService.save(newDataSync);

        }else {
            // dataSync.setPatientId(UUID.fromString(patient.getUuid()));
            dataSync.setPatientTableSyncDate(patient.getLastModified());
            dataSyncService.save(dataSync);
        }
    }
}
