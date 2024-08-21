package org.taxation.liability.infrastructure.exchange;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.taxation.liability.model.events.LiabilityCreated;
import org.taxation.liability.model.events.LiabilityTypeDefined;
import org.taxation.liability.model.events.PersonDeclared;

@Component
public class RabbitMQEventListener {

    @RabbitListener(queues = "#{queue.name}")
    public void handleOrderCreatedEvent(PersonDeclared event) {
        System.out.println("Received PersonDeclared: " + event);
    }

    @RabbitListener(queues = "#{queue.name}")
    public void handleOrderConfirmedEvent(LiabilityCreated event) {
        System.out.println("Received LiabilityCreated: " + event);
    }

    @RabbitListener(queues = "#{queue.name}")
    public void handleOrderShippedEvent(LiabilityTypeDefined event) {
        System.out.println("Received LiabilityTypeDefined: " + event);
    }
}
