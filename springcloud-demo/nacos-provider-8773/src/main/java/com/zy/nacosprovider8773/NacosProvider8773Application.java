package com.zy.nacosprovider8773;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosProvider8773Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosProvider8773Application.class, args);
    }

}
