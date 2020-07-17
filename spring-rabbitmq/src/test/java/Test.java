import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-rabbit.xml"})
public class Test {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @org.junit.Test
    public void test(){
        Map<String,String> map = new HashMap<>();
        map.put("name","zhouyang");
        rabbitTemplate.convertAndSend("msg.qq",map);
    }
}
