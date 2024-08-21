package org.taxation.liability.infrastructure.exchange;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Value("${rabbitmq.exchange.name}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @EventHandler
    public void on(Object event) {
        // Publish the event to RabbitMQ
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, event);
    }
}
