package com.zy.controller;

import com.zy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
//    //请求是/hello,返回的试图名是index，dispatcherServlet会根据springmvc的配置去找WEB-INF/views/index.jsp页面
//    @GetMapping("/")
//    public String hello(String name){
//        return "redirect:/login.html";
//    }
    @GetMapping("/admin")
    @ResponseBody
    public String hasAdmin(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
        return "你拥有admin权限";
    }
    @GetMapping("/admin/jsr")
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    public String hasAdminJsr(){
        return "你拥有admin方法权限";
    }
    @GetMapping("/user")
    @ResponseBody
    public String hasUser(){
        return "你拥有user权限";
    }
    
    @GetMapping("/user/jsr")
    @ResponseBody
    @Secured({"ROLE_USER"})
    public String hasUserJsr(){
        return "你拥有user方法权限";
    }
}
