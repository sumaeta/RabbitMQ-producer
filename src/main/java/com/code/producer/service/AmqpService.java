package com.code.producer.service;

import com.code.producer.dto.Message;

public interface AmqpService {

    void sendToConsumer(Message message);

}
