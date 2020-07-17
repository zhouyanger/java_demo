package com.zy;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");//rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        
        //默认交换器
        String queue_name = "queue_test";
        /**
         * 声明队列，如果Rabbit中没有此队列将自动创建 
         * param1:队列名称 
         * param2:是否持久化 
         * param3:队列是否独占此连接 
         * param4:队列不再使用时是否自动删除此队列 
         * param5:队列参数 
         */
        channel.queueDeclare(queue_name,true,false,false,null);
        /**
         * 监听队列String queue, boolean autoAck,Consumer callback 
         * 参数明细 
         * 1、队列名称 
         * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置 
         为false则需要手动回复 
         * 3、消费消息的方法，消费者接收到消息后调用此方法 
         */

        channel.basicConsume(queue_name,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body,"utf-8"));
        }
        });

        //direct交换器
        String directExchangexchangeName = "direct_exchange_inform";
        String directQqQueueName = "direct_qq_queue";
        String directQqRoutKey = "direct_qq_key";
        //表示声明了qq队列
        channel.queueDeclare(directQqQueueName,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(directQqQueueName,directExchangexchangeName,directQqRoutKey,null);
        channel.basicConsume(directQqQueueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("qq收到消息："+new String(body,"utf-8"));
            }
        });

        //fanout交换器
        String fanoutExchangexchangeName = "exchange_fanout_inform";
        String fanoutQueueNameEmail = "queue_fanout_email";
        String fanoutQueueNameQq = "queue_fanout_qq";
        String fanoutRoutKey = "";
        //表示声明了一个队列
        channel.queueDeclare(fanoutQueueNameEmail,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(fanoutQueueNameEmail,fanoutExchangexchangeName,fanoutRoutKey,null);
        //email接收消息
        channel.basicConsume(fanoutQueueNameEmail,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("email收到消息:"+new String(body,"utf-8"));
            }
        });
        //qq接收消息
        channel.queueDeclare(fanoutQueueNameQq,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(fanoutQueueNameQq,fanoutExchangexchangeName,fanoutRoutKey,null);
        channel.basicConsume(fanoutQueueNameQq,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("qq收到消息:"+new String(body,"utf-8"));
            }
        });

        //topic
        String topicExchangeName = "exchange_topic_inform";
        String topicQueueNameEmail = "queue_fanout_email";
        String topicQueueNameQq = "queue_fanout_qq";
        String topicQueueNameAll = "queue_fanout_All";
        String topicRoutKey1 = "#.email";  //接收email为后缀的路由键消息
        String topicRoutKey2 = "#.qq";  //匹配qq为尾的消息
        String topicRoutKey3 = "info.#"; //匹配info开头的消息
        //声明队列
        channel.queueDeclare(topicQueueNameEmail,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(topicQueueNameEmail,topicExchangeName,topicRoutKey1,null);
        //email接收消息
        channel.basicConsume(topicQueueNameEmail,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("email收到消息:"+new String(body,"utf-8"));
            }
        });
        //qq接收消息
        channel.queueDeclare(topicQueueNameQq,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(topicQueueNameQq,topicExchangeName,topicRoutKey2,null);
        //email接收消息
        channel.basicConsume(topicQueueNameQq,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("qq收到消息:"+new String(body,"utf-8"));
            }
        });
        //接收所有消息
        channel.queueDeclare(topicQueueNameAll,true,false,false,null);
        //建立一个绑定关系
        channel.queueBind(topicQueueNameAll,topicExchangeName,topicRoutKey3,null);
        //email接收消息
        channel.basicConsume(topicQueueNameAll,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("all收到消息:"+new String(body,"utf-8"));
            }
        });
        
        //header
        String headerExchangeName = "exchange_header_inform";
        //email接收消息
        String headerQueueNameEmail = "queue_header_email";
        Map<String,Object> emailMap  = new Hashtable<>();
        emailMap.put("info_type","email");
        channel.queueDeclare(headerQueueNameEmail,true,false,false,null);
        channel.queueBind(headerQueueNameEmail,headerExchangeName,"",emailMap);
        channel.basicConsume(headerQueueNameEmail,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("email收到消息:"+new String(body,"utf-8"));
            }
        });
        //qq
        String headerQueueNameQq = "queue_header_qq";
        Map<String,Object> qqMap  = new Hashtable<>();
        qqMap.put("info_type","qq");
        channel.queueDeclare(headerQueueNameQq,true,false,false,null);
        channel.queueBind(headerQueueNameQq,headerExchangeName,"",qqMap);
        channel.basicConsume(headerQueueNameQq,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("qq收到消息:"+new String(body,"utf-8"));
            }
        });
    }
}
