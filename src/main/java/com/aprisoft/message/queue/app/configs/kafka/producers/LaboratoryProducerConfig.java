package com.aprisoft.message.queue.app.configs.kafka.producers;

import com.aprisoft.message.queue.app.entities.entity.views.LaboratoryView;
import com.aprisoft.message.queue.app.utilities.ConstantsUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.List;

@Configuration
public class LaboratoryProducerConfig {
    @Bean
    public ProducerFactory<String, LaboratoryView> labProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, LaboratoryView> labKafkaTemplate() {
        return new KafkaTemplate<>(labProducerFactory());
    }

    @Bean
    public ProducerFactory<String, List<LaboratoryView>> labsProducerFactory() {
        //
        return ConstantsUtils.getFactory();
    }

    @Bean
    public KafkaTemplate<String, List<LaboratoryView>> labsListKafkaTemplate() {
        return new KafkaTemplate<>(labsProducerFactory());
    }
}
