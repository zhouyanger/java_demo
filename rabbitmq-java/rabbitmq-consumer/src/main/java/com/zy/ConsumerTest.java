package com.zy;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTest {
    private static  final String QUE_NMAE="queue_two";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_FANOUT_INFORM="exchange_fanout_inform";
    private static final String EXCHANGE_DIRECT_INFORM="exchange_direct_inform";
    private static final String EXCHANGE_TOPICS_INFORM="exchange_topics_inform";
    private static final String ROUT_SMS="rout_sms";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = null;
        Channel channel = null;
        try {
           connection = ConnectionFactoryUtils.newConnection();
            channel = connection.createChannel();
//            channel.queueDeclare(QUE_NMAE,true,false,false,null);
//            while (true) {
//                //定义消费方法
//                DefaultConsumer consumer = new DefaultConsumer(channel) {
//                    /**
//                     * 消费者接收消息调用此方法
//                     *
//                     * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
//                     * @param envelope    消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
//                     * @param properties
//                     * @param body
//                     * @throws IOException
//                     */
//                    @Override
//                    public void handleDelivery(String consumerTag,
//                                               Envelope envelope,
//                                               AMQP.BasicProperties properties,
//                                               byte[] body)
//                            throws IOException {
//                        //交换机
//                        String exchange = envelope.getExchange();
//                        //路由key
//                        String routingKey = envelope.getRoutingKey();
//                        //消息id
//                        long deliveryTag = envelope.getDeliveryTag();
//                        //消息内容
//                        String msg = new String(body, "utf-8");
//                        System.out.println("消费者1:" + msg);
//                    }
//                };
//                channel.basicConsume(QUE_NMAE, true, consumer);
//            }
            //发布订阅模式消费
//            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
//            channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);
//            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_FANOUT_INFORM,"");
//            channel.basicConsume(QUEUE_INFORM_SMS,true,new DefaultConsumer(channel){
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    long deliveryTag = envelope.getDeliveryTag();
//                    String exchange = envelope.getExchange();
//                    //消息内容 
//                    String message = new String(body, "utf-8");
//                    System.out.println("sms接收到消息:"+message);
//                }
//            });

            //rout模式
            channel.exchangeDeclare(EXCHANGE_DIRECT_INFORM, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);
            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_DIRECT_INFORM,ROUT_SMS);
            channel.basicConsume(QUEUE_INFORM_SMS,true,new DefaultConsumer(channel){
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    long deliveryTag = envelope.getDeliveryTag();
                    String exchange = envelope.getExchange();
                    //消息内容 
                    String message = new String(body, "utf-8");
                    System.out.println("email接收到消息:"+message);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
