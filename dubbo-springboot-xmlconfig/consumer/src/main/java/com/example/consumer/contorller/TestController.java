package com.example.consumer.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zy.dao.DemoService;
import com.zy.entity.Student;

@RestController
public class TestController {
    @Autowired
    @Qualifier("demoService")
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
