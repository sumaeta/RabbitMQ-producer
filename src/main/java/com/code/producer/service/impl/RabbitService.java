package com.code.producer.service.impl;

import com.code.producer.amqp.AmqpProducer;
import com.code.producer.dto.Message;
import com.code.producer.service.AmqpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService implements AmqpService {

    @Autowired
    private AmqpProducer<Message> amqp;

    @Override
    public void sendToConsumer(Message message) {
        amqp.producer(message);
    }
}
