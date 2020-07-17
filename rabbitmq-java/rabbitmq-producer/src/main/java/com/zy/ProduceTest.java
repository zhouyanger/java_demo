package com.zy;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class ProduceTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");//rabbitmq默认虚拟机名称为“/”，虚拟机相当于一个独立的mq服
        Connection connection = null;
        Channel channel = null;
        try {
            //创建与RabbitMQ服务的TCP连接 
             connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            
            //默认交换器
            String queue_name = "queue_test";
             for (int i=0;i<10;i++) {
                 String message = "消息》》" + i;
                 /**
                  * 消息发布方法 
                  * param1：Exchange的名称，如果没有指定，则使用Default Exchange 
                  * param2:routingKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列 ,默认交换机的key是队列名
                  * param3:消息包含的属性 
                  * param4：消息体 
                  * */
                  channel.basicPublish("",queue_name,null,message.getBytes());   

             }
             
             //direct交换机
            String directExchangeName = "direct_exchange_inform";
            String directQqRoutKey = "direct_qq_key";
             String directMessage = "direct消息";
            //声明交换机
            channel.exchangeDeclare(directExchangeName,BuiltinExchangeType.DIRECT);
            //发布消息
            channel.basicPublish(directExchangeName,directQqRoutKey,null,directMessage.getBytes());
            
            
            //fanout
            String fanoutExchangeName = "exchange_fanout_inform";
            //路由键为空
             String fanoutRoutKey = "";
             String fanoutMessage = "fanout消息";
            channel.exchangeDeclare(fanoutExchangeName,BuiltinExchangeType.FANOUT);
            channel.basicPublish(fanoutExchangeName,fanoutRoutKey,null,fanoutMessage.getBytes());

            //topic
            String topicExchangeName = "exchange_topic_inform";
            String topicRoutKey1 = "info.email";
            String topicRoutKey2 = "info.qq";
            String topicMessage1 = "topic的email消息消息";
            String topicMessage2 = "topic的qq消息";
            channel.exchangeDeclare(topicExchangeName,BuiltinExchangeType.TOPIC);
            channel.basicPublish(topicExchangeName,topicRoutKey1,null,topicMessage1.getBytes());
            channel.basicPublish(topicExchangeName,topicRoutKey2,null,topicMessage2.getBytes());
            
            //header
            String headerExchangeName = "exchange_header_inform";
            //发送email
            Map<String,Object> emailMap  = new Hashtable<>();
            emailMap.put("info_type","email");
            String headMessage1 = "header的email消息";
            channel.exchangeDeclare(headerExchangeName,BuiltinExchangeType.HEADERS);
            Builder builder = new BasicProperties().builder();
            builder.headers(emailMap);
            channel.basicPublish(headerExchangeName,"",builder.build(),headMessage1.getBytes());
            //发送qq
            Map<String,Object> qqMap  = new Hashtable<>();
            qqMap.put("info_type","qq");
            String headMessage2 = "header的qq消息";
            channel.exchangeDeclare(headerExchangeName,BuiltinExchangeType.HEADERS);
            Builder builderQQ = new BasicProperties().builder();
            builderQQ.headers(qqMap);
            channel.basicPublish(headerExchangeName,"",builderQQ.build(),headMessage2.getBytes());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (channel!=null){
                channel.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
    }
}