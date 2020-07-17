package com.zy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//spring的配置文件，扫描包，除了controller注解
@Configuration
@ComponentScan(value = "com.zy",excludeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Controller.class})})
public class SpringCnfiguration {
}
