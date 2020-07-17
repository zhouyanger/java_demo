package com.zy.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "qqQueue")
public class MyRabbitListenner {
    @RabbitHandler
    public void getMessage(Map map){
        System.out.println("qq接收到的消息"+map);
    }
}
