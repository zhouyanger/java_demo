package com.zy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testsession2Controller {
    @RequestMapping(value = "/a/a1",produces = {"application/json;charset=UTF-8"})
    public String Hello1(HttpServletRequest request){
        return  (String)request.getSession().getAttribute("name")+":>"+request.getSession().getId();
    }
   
}
