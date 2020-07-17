package com.zy.dao;

import com.zy.entity.Student;

public class DemoServiceImpl implements DemoService{
    @Override
    public String sayHello(String name) {
        return "你好"+name;
    }

    @Override
    public Student getStudent(int id) {
        Student student = new Student();
        student.setId(1);
        student.setName("zhouyang");
        student.setAge(25);
        return student;
    }
}
