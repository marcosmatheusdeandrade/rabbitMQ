package com.rabbit.service;

import com.rabbit.dto.TestDTO;
import com.rabbit.enums.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Queue queueName, TestDTO testDTO) {
        rabbitTemplate.convertAndSend(queueName.getName(), testDTO);
    }

}
