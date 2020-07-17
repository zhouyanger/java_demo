package com.zy.controller;

import com.zy.dao.DemoService;
import com.zy.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    @Qualifier("demoService")
    private DemoService demoService;
    @RequestMapping(value = "/hello",produces = {"application/json;charset=UTF-8"})
    public String sayHello(){
        return demoService.sayHello("zhouyang");
    }
    @RequestMapping("/get_student")
    public Student getStudent(int id){
        Student student = demoService.getStudent(id);
        return student;
    }
}
