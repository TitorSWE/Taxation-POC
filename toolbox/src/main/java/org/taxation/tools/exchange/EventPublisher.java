<<<<<<< HEAD
package org.taxation.tools.exchange;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Value("${rabbitmq.exchange.name}")
    private String fanoutExchange;

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;

    public EventPublisher(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    @EventHandler
    public void on(Object event) {
        // Publish the event to RabbitMQ
        MessageProperties properties = new MessageProperties();
        Message message = messageConverter.toMessage(event, properties);
        rabbitTemplate.send(fanoutExchange, message);
    }
=======
package org.taxation.tools.exchange;public class EventPublisher {
>>>>>>> 34f407bdf061ef716a6c180731a0f8926eb95942
}
