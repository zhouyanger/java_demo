package com.example.consumer.contorller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zy.dao.DemoService;
import com.zy.entity.Student;

@RestController
public class TestController {
    @Reference
    private DemoService demoService;
    @RequestMapping("hello")
    public String sayHello(){
      return  demoService.sayHello("zhouyang");
    }
    @RequestMapping("get_student")
    public Student getStudent(){
        return demoService.getStudent(1);
    }
    
}
