package com.zy.serviceribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value="hello",produces = "application/json;charset=UTF-8")
    @HystrixCommand(fallbackMethod = "hystHello")
    public String say(){
        String forObject = restTemplate.getForObject("http://eureka-provider/hello", String.class);
        return "robbon调用>>>"+forObject;
    }
   public String hystHello(){
        return "服务器down了";
   }
    
}
