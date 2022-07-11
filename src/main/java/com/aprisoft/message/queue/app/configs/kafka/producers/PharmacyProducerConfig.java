package com.aprisoft.message.queue.app.configs.kafka.producers;

import com.aprisoft.message.queue.app.entities.entity.views.PharmacyView;
import com.aprisoft.message.queue.app.utilities.ConstantsUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.List;

@Configuration
public class PharmacyProducerConfig {
    @Bean
    public ProducerFactory<String, PharmacyView> pharmProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, PharmacyView> pharmKafkaTemplate() {
        return new KafkaTemplate<>(pharmProducerFactory());
    }

    @Bean
    public ProducerFactory<String, List<PharmacyView>> pharmsListProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, List<PharmacyView>> pharmsListKafkaTemplate() {
        return new KafkaTemplate<>(pharmsListProducerFactory());
    }
}
