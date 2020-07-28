package com.zy.auu.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    private static final String SESSION_KEY_SMS="user_mobile_code";
    @PostMapping("/authentication/mobile")
    public String getMobileCode(HttpServletRequest request){
        String code = "1234";
        request.getSession().setAttribute(SESSION_KEY_SMS,code);
        return code;
    }
}
