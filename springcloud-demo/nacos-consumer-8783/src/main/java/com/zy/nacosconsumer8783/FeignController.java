package com.zy.nacosconsumer8783;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Autowired
    private MyFeignInterface myFeignInterface;
    @RequestMapping("hello")
    public String sayHello(){
       return "feign"+myFeignInterface.sayHello();
    }
}
