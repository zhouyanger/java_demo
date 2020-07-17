package com.zy.springbootdemo.controller;

import com.zy.springbootdemo.entity.Student;
import com.zy.springbootdemo.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {
    @Resource(name = "student")
    private Student student;
    
    @Resource
    private Teacher teacher;
    @RequestMapping("/hello")
    public String hello(){
        Logger logger = LoggerFactory.getLogger(HelloController.class);
        logger.info("您好");
        return "欢饮使用springboot"+student+">>"+teacher;
    }

    @RequestMapping("/testExcp")
    public String testExcp(){
        int a = 3/0;
       return "你好";
    }
}
