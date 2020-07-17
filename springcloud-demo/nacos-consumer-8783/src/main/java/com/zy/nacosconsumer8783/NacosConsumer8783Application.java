package com.zy.nacosconsumer8783;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class NacosConsumer8783Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumer8783Application.class, args);
    }

}
