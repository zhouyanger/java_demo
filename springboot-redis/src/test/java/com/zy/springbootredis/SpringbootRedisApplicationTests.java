package com.zy.springbootredis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringbootRedisApplicationTests {
@Autowired
private RedisTemplate<String,Object> redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("myname","zhouyang");
        String myname = (String)redisTemplate.opsForValue().get("myname");
        System.out.println(myname);
    }
    @Test
    void testPublish() {
        redisTemplate.convertAndSend("chat","我是周扬");
    }
}

