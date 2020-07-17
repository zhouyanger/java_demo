package com.zy.servicefeign8781;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private MyFeignInterface myFeignInterface;
    @RequestMapping("hello")
    public String testHello(){
       return "feign调用>>>"+myFeignInterface.say();
    }
}
