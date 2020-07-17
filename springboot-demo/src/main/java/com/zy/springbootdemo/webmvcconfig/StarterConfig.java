package com.zy.springbootdemo.webmvcconfig;

import com.zy.springbootdemo.handle.HelloApplicationRunner;
import com.zy.springbootdemo.handle.HelloCommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarterConfig {
    @Bean
    public HelloApplicationRunner helloApplicationRunner(){
        return new HelloApplicationRunner();
    }
    @Bean
    public HelloCommandLineRunner helloCommandLineRunner(){
        return new HelloCommandLineRunner();
    }
}
