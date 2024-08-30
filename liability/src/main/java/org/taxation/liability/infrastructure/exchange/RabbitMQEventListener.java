package org.taxation.liability.infrastructure.exchange;

import org.axonframework.serialization.Serializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.taxation.liability.model.events.LiabilityCreated;
import org.taxation.liability.model.events.LiabilityTypeDefined;
import org.taxation.liability.model.events.PersonDeclared;

@Component
public class RabbitMQEventListener {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQEventListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "#{queue.name}")
    public void handleOrderCreatedEvent(Message event) {
        Object object = this.rabbitTemplate.getMessageConverter().fromMessage(event);
        System.out.println("Received : " + object.getClass().getName() + "    " + object.toString());
    }

}
