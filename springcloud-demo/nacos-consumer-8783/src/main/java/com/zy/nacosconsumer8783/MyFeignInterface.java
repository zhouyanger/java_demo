package com.zy.nacosconsumer8783;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("nacos-provider")
public interface MyFeignInterface {
    @RequestMapping("/hello")
    public String sayHello();
}
