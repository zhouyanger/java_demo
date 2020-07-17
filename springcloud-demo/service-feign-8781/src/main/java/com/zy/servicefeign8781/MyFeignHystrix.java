package com.zy.servicefeign8781;

import org.springframework.stereotype.Component;

@Component
public class MyFeignHystrix implements MyFeignInterface{
    @Override
    public String say() {
        return "feign调用服务降级了";
    }
}
