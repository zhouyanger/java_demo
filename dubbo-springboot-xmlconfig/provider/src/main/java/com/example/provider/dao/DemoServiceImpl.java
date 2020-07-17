package com.example.provider.dao;

import org.springframework.stereotype.Service;

import com.zy.dao.DemoService;
import com.zy.entity.Student;

@Service("demoService")
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "service"+name;
    }

    @Override
    public Student getStudent(int id) {
        Student student = new Student();
        student.setId(2);
        student.setName("service");
        student.setAge(25);
        return student;
    }
}
