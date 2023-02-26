package com.rabbit.connections;


import com.rabbit.enums.Queue;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMqConnection {

    private static final String EXCHANGE = "test.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private org.springframework.amqp.core.Queue queue(String name) {
        return new org.springframework.amqp.core.Queue(name, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(EXCHANGE);
    }

    private Binding relationship(org.springframework.amqp.core.Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void fill() {
        org.springframework.amqp.core.Queue queue = queue(Queue.TEST.getName());

        DirectExchange directExchange = trocaDireta();

        Binding binding = relationship(queue, directExchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareExchange(directExchange);
        this.amqpAdmin.declareBinding(binding);
    }
}
