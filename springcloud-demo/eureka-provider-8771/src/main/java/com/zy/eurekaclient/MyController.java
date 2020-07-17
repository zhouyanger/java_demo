package com.zy.eurekaclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor.STRING;
import javax.servlet.ServletRequest;

@RestController
public class MyController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("hello")
    @HystrixCommand(fallbackMethod = "myFallbackMethod")
    public String say() {
        return "hello:"+ port;
    }
    public String myFallbackMethod(){
        return "服务器发生异常";
    }
    @RequestMapping("/test/test_gateway")
    public String testGateway(String name) {
        return "test gateway:"+name+ "端口号："+port;
    }
}
