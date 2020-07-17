package com.zy.entity;

import lombok.Data;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Data
public class Student {
    AbstractAnnotationConfigDispatcherServletInitializer 
    private String name;
    private Integer age;
}
