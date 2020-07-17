package com.example.provider.dao;

import com.alibaba.dubbo.config.annotation.Service;
import com.zy.dao.DemoService;
import com.zy.entity.Student;

@org.springframework.stereotype.Service("demoService")
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "你好"+name;
    }

    @Override
    public Student getStudent(int id) {
        Student student = new Student();
        student.setId(2);
        student.setName("api");
        student.setAge(25);
        return student;
    }
}
