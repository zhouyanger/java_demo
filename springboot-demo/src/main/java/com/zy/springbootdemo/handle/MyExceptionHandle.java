package com.zy.springbootdemo.handle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandle {
    
    @ExceptionHandler(value = {RuntimeException.class})
    public String get(Exception e, HttpServletRequest request){
        request.setAttribute("javax.servlet.error.status_code",300);
        Map<String,String> map = new HashMap<>();
        map.put("code","400");
        map.put("msg","发生错误");
        request.setAttribute("tx",map);
        return "forward:/error";
    }
}
