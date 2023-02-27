package com.rabbit.controller;

import com.rabbit.dto.TestDTO;
import com.rabbit.enums.Queue;
import com.rabbit.service.RabbitMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    private RabbitMqService rabbitMqService;

    @Autowired
    public TestController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @PostMapping
    private ResponseEntity test(@RequestBody TestDTO testDTO) {
        rabbitMqService.sendMessage(Queue.TEST, testDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
