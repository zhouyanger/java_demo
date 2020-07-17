package com.zy.servicegatewaydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class ServiceGatewayDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayDemoApplication.class, args);
    }
}
