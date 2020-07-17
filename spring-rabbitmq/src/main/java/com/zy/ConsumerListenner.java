package com.zy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerListenner implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(message.toString());
    }
}
