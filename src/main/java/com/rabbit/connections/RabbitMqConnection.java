package com.rabbit.connections;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMqConnection {

    public static final String TEST_QUEUE = "testQueue";
    private static final String EXCHANGE = "test.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMqConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String name) {
        return new Queue(name, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(EXCHANGE);
    }

    private Binding relationship(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    @PostConstruct
    private void fill() {
        Queue queue = queue(TEST_QUEUE);

        DirectExchange directExchange = trocaDireta();

        Binding binding = relationship(queue, directExchange);

        this.amqpAdmin.declareQueue(queue);
        this.amqpAdmin.declareExchange(directExchange);
        this.amqpAdmin.declareBinding(binding);
    }
}
