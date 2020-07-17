package com.zy.springbootdemo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@PropertySource("classpath:db.properties")
@ConfigurationProperties(prefix = "student")
public class Student {
    private String name;
    private int age;
}
