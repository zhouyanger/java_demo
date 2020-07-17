package com.zy.annotion;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data
@Component
@Lazy(true)
public class Book {
    @Value("语文")
    private String name;
    public void init(){
        System.out.println("init方法");
    }
    public void destory(){
        System.out.println("destory方法");
    }
}
