package com.aprisoft.message.queue.app.configs.kafka.producers;

import com.aprisoft.message.queue.app.entities.entity.views.ClinicView;
import com.aprisoft.message.queue.app.utilities.ConstantsUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.List;

@Configuration
public class ClinicProducerConfig {

    @Bean
    public ProducerFactory<String, ClinicView> clinicProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, ClinicView> clinicKafkaTemplate() {
        return new KafkaTemplate<>(clinicProducerFactory());
    }

    @Bean
    public ProducerFactory<String, List<ClinicView>> clinicListProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, List<ClinicView>> clinicListsKafkaTemplate() {
        return new KafkaTemplate<>(clinicListProducerFactory());
    }
}
