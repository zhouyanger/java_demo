package com.zy.listener;

import lombok.Data;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Data
public class RedisMessageListener implements MessageListener {
    private StringRedisSerializer stringRedisSerializer;
    private JdkSerializationRedisSerializer jdkSerializationRedisSerializer;
    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        // 使用字符串序列化器转换
        String deserialize = stringRedisSerializer.deserialize(body);
        System.out.println("消息是:"+deserialize);
        byte[] channel = message.getChannel();
        // 使用字符串序列化器转换
        String channelDeserialize = stringRedisSerializer.deserialize(channel);
        System.out.println("chennel是:"+channelDeserialize);
        String deserialize1 = stringRedisSerializer.deserialize(bytes);
        System.out.println(">>>"+deserialize1);
    }
}
