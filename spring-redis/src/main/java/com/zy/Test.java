package com.zy;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPSize;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import javax.naming.ldap.Rdn;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        RedisTemplate redisTemplate = applicationContext
                .getBean(RedisTemplate.class);
////         设值
//        redisTemplate.opsForValue().set("key1", "value1");
//        redisTemplate.opsForValue().set("key2", "value2");
//        // 通过key获取值
//        String value1 = (String) redisTemplate.opsForValue().get("key1");
//        System.out.println(value1);
//        // 通过key删除值
//        redisTemplate.delete("key1");
//        // 求长度
//        Long length = redisTemplate.opsForValue().size("key2");
//        System.out.println(length);
//        // 设值新值并返回旧值
//        String oldValue2 = (String) redisTemplate.opsForValue().getAndSet(
//                "key2", "new_value22");
//        System.out.println(oldValue2);
//        // 通过key获取值.
//        String value2 = (String) redisTemplate.opsForValue().get("key2");
//        System.out.println(value2);
//        // 求子串
//        String rangeValue2 = redisTemplate.opsForValue().get("key2", 0, 3);
//        System.out.println(rangeValue2);
//        // 追加字符串到末尾，返回新串长度
//        int newLen = redisTemplate.opsForValue().append("key2", "_app");
//        System.out.println(newLen);
//        String appendValue2 = (String) redisTemplate.opsForValue().get("key2");
//        System.out.println(appendValue2);
//        
        
        
        //hash操作
//        String key ="hash";
//        Map<String,String> map = new HashMap<>();
//        map.put("f1","val1");
//        map.put("f2","val2");
//        redisTemplate.opsForHash().putAll(key,map);
//        redisTemplate.opsForHash().put(key,"f1","4");
//        printHash(redisTemplate,key,"f1");
        
        //zset操作
        
        
        //事务
//        List<Object> execute = (List<Object>)redisTemplate.execute(new SessionCallback<List<Object>>() {
//            @Override
//            public List<Object> execute(RedisOperations redisOperations) throws DataAccessException {
//                redisOperations.multi();
//                redisOperations.boundValueOps("key1").set("zhouyang");
//                String key1 = (String) redisOperations.boundValueOps("key1").get();
//                System.out.println(key1);
//                List exec = redisOperations.exec();
//                return exec;
//            }
//        });
//        System.out.println(execute);
        
        //测试流水线
        //测试订阅发布
        Scanner scanner = new Scanner(System.in);
        while(true){
            if (scanner.hasNext()){
                String next = scanner.next();
                if ("no".equals(next)){
                    break;
                }
                redisTemplate.convertAndSend("chat",next);
            }
        }
        
        //测试超时，设置了超时，redis不会立马回收，采用定时回收或惰性回收，定时回收缺点：如果超时的数据很多会影响性能，惰性是调用get时回收。
        Object execute = redisTemplate.execute((new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.boundValueOps("name").set("zhouyang");
                Long name = redisOperations.getExpire("name");
                System.out.println(name);
                redisOperations.expire("name", 300, TimeUnit.SECONDS);
                return null;
            }
        }));
    }
    
    public static void printHash(RedisTemplate redisTemplate,String key,String filed){
        System.out.println(redisTemplate.opsForHash().get(key,filed));
    }
}
