package com.aprisoft.message.queue.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class.getName());

    /*@KafkaListener(topics = ConstantsUtils.PATIENT_TOPIC, groupId = ConstantsUtils.PATIENT_CONSUMER_GROUP)
    public void consume(String message) {
        logger.info(String.format("$$$$ => Consumed message: %s", message));
    }*/

}
