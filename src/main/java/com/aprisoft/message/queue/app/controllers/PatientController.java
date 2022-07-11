package com.aprisoft.message.queue.app.controllers;

import com.aprisoft.message.queue.app.entities.entity.views.PatientView;
import com.aprisoft.message.queue.app.services.DataSyncService;
import com.aprisoft.message.queue.app.services.PatientService;
import com.aprisoft.message.queue.app.services.producers.impl.PatientProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final KafkaTemplate<String, List<PatientView>> patientsKafkaTemplate;
    private final KafkaTemplate<String, PatientView> patientKafkaTemplate;
    private final PatientService patientService;
    private final PatientProducer patientProducer;

    private final DataSyncService dataSyncService;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class.getSimpleName());

    public PatientController(
            KafkaTemplate<String, List<PatientView>> patientsKafkaTemplate,
            KafkaTemplate<String, PatientView> patientKafkaTemplate,
            PatientService patientService,
            PatientProducer patientProducer, DataSyncService dataSyncService
    ) {
        this.patientsKafkaTemplate = patientsKafkaTemplate;
        this.patientKafkaTemplate = patientKafkaTemplate;
        this.patientService = patientService;
        this.patientProducer = patientProducer;
        this.dataSyncService = dataSyncService;
    }

    @GetMapping("/sync")
    public String syncPatient(

    ){
        logger.info("Getting patient to be synced");
        // return patientProducer.();
        return "Test";
    }

}
