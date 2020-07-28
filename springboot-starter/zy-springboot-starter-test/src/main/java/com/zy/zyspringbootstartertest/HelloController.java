package com.zy.zyspringbootstartertest;

import com.zy.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {
    @Resource
    private HelloService helloService;
    @RequestMapping("hello")
    public String hello(){
        return helloService.say();
    }
}
