package com.zy.servicefeign8781;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@FeignClient(value = "eureka-provider",fallback = MyFeignHystrix.class)
public interface MyFeignInterface {
    @RequestMapping("hello")
    public String say();
}
