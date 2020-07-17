package com.zy.entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Student {
    private String name;
    private Integer age;
    public void init(){
        System.out.println("init方法");
    }
    public void destory(){
        System.out.println("destory方法");
    }
}
