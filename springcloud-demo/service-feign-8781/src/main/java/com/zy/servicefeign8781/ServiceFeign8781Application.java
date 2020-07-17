package com.zy.servicefeign8781;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ServiceFeign8781Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeign8781Application.class, args);
    }

}
