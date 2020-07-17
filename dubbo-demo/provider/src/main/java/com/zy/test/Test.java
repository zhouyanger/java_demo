package com.zy.test;

import com.zy.dao.DemoServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.naming.Context;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        DemoServiceImpl demoService = (DemoServiceImpl) applicationContext.getBean("demoService");
        System.out.println(demoService);
        applicationContext.start();
        try {
            System.in.read();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
