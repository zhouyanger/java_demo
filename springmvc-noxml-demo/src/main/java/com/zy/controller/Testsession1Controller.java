package com.zy.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.entity.Student;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testsession1Controller {
    @RequestMapping(value = "/s/s1")
    public String Hello1(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setAttribute("name","zhouyang");
        return (String)session.getAttribute("name")+":>"+request.getSession().getId();
    }
    @RequestMapping(value = "/s/s2")
    public String Hello2(HttpServletRequest request){
        return (String)request.getSession().getAttribute("name")+":>"+request.getSession().getId();
    }
}
