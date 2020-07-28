package com.zy.auu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuuApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuuApplication.class, args);
    }

}
