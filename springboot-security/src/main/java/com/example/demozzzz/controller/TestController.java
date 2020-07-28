package com.example.demozzzz.controller;

import com.example.demozzzz.sms.DefaultSmsCodeSender;
import com.example.demozzzz.sms.SmsCodeSender;
import com.example.demozzzz.sms.ValidateCode;
import com.sun.deploy.net.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
public class TestController {
    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @GetMapping("/code/sms")
     public String createSmsCode(HttpServletRequest request) throws ServletRequestBindingException {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        if (mobile==null || "".equalsIgnoreCase(mobile))
            throw  new RuntimeException("手机号码不能为空");
        //这里写死验证码，正常是调用三方获取验证码
        ValidateCode smsCode = new ValidateCode("123456",60);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,smsCode);
                SmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
                 smsCodeSender.send(mobile,smsCode.getCode());

        Object attribute = sessionStrategy.getAttribute(new ServletWebRequest(request), TestController.SESSION_KEY);
        return "123456";
             }
             
    
    @RequestMapping("/login-success")
    public String te(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()){
            return "";
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails){
     String username = ((UserDetails) principal).getUsername();
     return username;
        }
        return "";
    }

    @RequestMapping("/a/a1")
    public String a1(){
        return "hello admin";
    }
    
    @RequestMapping("/u/u1")
    public String u1(){
        return "hello user";
    }

    @RequestMapping("/s/s1")
    public String s1(){ return "hello s"; }
}
