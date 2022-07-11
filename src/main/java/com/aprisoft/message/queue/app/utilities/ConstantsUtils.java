package com.aprisoft.message.queue.app.utilities;

import com.aprisoft.message.queue.app.controllers.vm.SyncTableDetails;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantsUtils {
    public final static String BOOTSTRAP_SERVER = "127.0.0.1:9092";

    // Consumer
    public final static String PATIENT_CONSUMER_GROUP = "patient_consumer_group";

    public final static String GREETING_TOPIC = "topic_greeting";

    public final static DefaultKafkaProducerFactory getFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                ConstantsUtils.BOOTSTRAP_SERVER);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    public final static List<SyncTableDetails> SYNC_TABLES_DETAILS = Arrays.asList(
            new SyncTableDetails("patient", "Patient Table", 0, 0, null),
            new SyncTableDetails("client", "Client Table", 0, 0, null),
            new SyncTableDetails("laboratory", "Laboratory Table", 0, 0, null),
            new SyncTableDetails("pharmacy", "Pharmacy Table", 0, 0, null)
    );
}


