package org.taxation.tools.exchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMessageConverter implements MessageConverter {

    private final ObjectMapper objectMapper;

    public RabbitMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        String clasName = o.getClass().getName();
        messageProperties.setType(clasName);
        byte[] serializedContent;
        try {
            serializedContent = objectMapper.writeValueAsBytes(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Message(serializedContent, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        String className = message.getMessageProperties().getType();
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            return objectMapper.readValue(message.getBody(), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
