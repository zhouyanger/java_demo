package com.zy.controller;

import com.zy.entity.AuthenticationRequest;
import com.zy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class HelloController {
    @GetMapping(value = "/r/r1",produces = "application/json;charset=utf-8")
    public String r1(){
        return "获取r1资源成功";
    }
    @GetMapping(value = "/s/s1",produces = "application/json;charset=utf-8")
    public String s1(){
        return "获取s1资源成功";
    }
    @GetMapping(value = "/a/a1",produces = "application/json;charset=utf-8")
    public String a1(){
        return "获取a1资源成功";
    }
    @RequestMapping(value = "/login-success",produces = {"application/json;charset=UTF-8"})
    public String loginSuccess(){
        return " 登录成功";
    }
}
