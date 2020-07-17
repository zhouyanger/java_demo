package com.zy.configclientdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ConfigProperties configProperties;
    @RequestMapping("/test")
    public String getConfig(){
        return configProperties.toString();
    }
}
