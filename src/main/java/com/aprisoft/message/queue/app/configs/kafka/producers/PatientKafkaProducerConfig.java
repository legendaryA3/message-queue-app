package com.aprisoft.message.queue.app.configs.kafka.producers;

import com.aprisoft.message.queue.app.entities.entity.views.PatientView;
import com.aprisoft.message.queue.app.utilities.ConstantsUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.List;

@Configuration
public class PatientKafkaProducerConfig {

    @Bean
    public ProducerFactory<String, PatientView> patientProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, PatientView> patientKafkaTemplate() {
        return new KafkaTemplate<>(patientProducerFactory());
    }

    @Bean
    public ProducerFactory<String, List<PatientView>> patientsProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, List<PatientView>> patientsKafkaTemplate() {
        return new KafkaTemplate<>(patientsProducerFactory());
    }


}
