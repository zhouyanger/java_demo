package com.zy.rabbitmqproducer;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitmqProducerApplicationTests {
@Autowired
private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
    }
    @Test
    void testProducer(){
        Map<String,String> map = new HashMap<>();
        map.put("name","zhouyang");
    rabbitTemplate.convertAndSend("topic_exchange","info.qq",map);
}
}
