package com.zy.annotion;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
public class AopTest {
    public String say(){
        System.out.println("我是aoptest");
        return "aop";
    }
}
