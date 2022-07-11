package com.aprisoft.message.queue.app.configs.kafka;

import com.aprisoft.message.queue.app.utilities.ConstantsUtils;
import com.aprisoft.message.queue.app.utilities.kafka.TopicsConstantsUtils;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, ConstantsUtils.BOOTSTRAP_SERVER);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic patientTableTopic() {
        return new NewTopic(TopicsConstantsUtils.PATIENT_TOPIC, 1, (short) 1);
    }
    @Bean
    public NewTopic greetingTopic() {
        return new NewTopic(ConstantsUtils.GREETING_TOPIC, 1, (short) 1);
    }
    @Bean
    public NewTopic patientsTableTopic() {
        return new NewTopic(TopicsConstantsUtils.PATIENTS_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic clinicTableTopic() {
        return new NewTopic(TopicsConstantsUtils.CLINIC_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic clinicsTableTopic() {
        return new NewTopic(TopicsConstantsUtils.CLINICS_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic labTableTopic() {
        return new NewTopic(TopicsConstantsUtils.LABORATORY_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic labsTableTopic() {
        return new NewTopic(TopicsConstantsUtils.LABORATORIES_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic pharmTableTopic() {
        return new NewTopic(TopicsConstantsUtils.PHARMACY_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic pharmsTableTopic() {
        return new NewTopic(TopicsConstantsUtils.PHARMACIES_TOPIC, 1, (short) 1);
    }
}
