package com.zy.rabbitmqproducer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqProducerConfig {
    //交换机配置
    @Bean
    public Exchange testExchange(){
        return ExchangeBuilder.topicExchange("topic_exchange").durable(true).build();
    }
    //声明一个队列
    @Bean
    public Queue testQueue(){
        return new Queue("qqQueue",true,false,false);
    }
    //绑定队列和交换机
    @Bean
    public Binding tesBinding(){
        return BindingBuilder.bind(testQueue()).to(testExchange()).with("info.#").noargs();
    }
}
