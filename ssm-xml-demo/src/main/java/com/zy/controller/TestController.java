package com.zy.controller;

import com.zy.dao.TeacherDao;
import com.zy.entity.Student;
import com.zy.entity.Teacher;
import com.zy.service.TeacherService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class TestController {
    @Resource
    private TeacherService teacherService;
    @RequestMapping(value = "/hello",produces={"application/json;","text/html;charset=UTF-8;"})
    @ResponseBody
    public String Hello(String name){
        System.out.println(name);
        return "周杨";
    }
    //测试jsp页面
    @RequestMapping("/test")
    public String showTest(HttpServletRequest request, Map map){
        String name = request.getParameter("name");
        System.out.println("前端传来的参数是:"+name);
        Student student = new Student();
        student.setName("周杨");
        map.put("studnet",student);
        return "index";
    }
    
    //测试json
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    @ResponseBody
    public Student test1(){
        Student student = new Student();
        student.setName("周杨");
        return student;
    }
    @RequestMapping("/test2/{id}")
    public String test2(@PathVariable("id")String id){
        System.out.println(id);
        return "success";
    }
    //测试异常处理
    @RequestMapping("/test3")
    @ResponseBody
    public String test3(){
        int a = 4/0;
        return "success";
    }

    @RequestMapping("/test4")
    @ResponseBody
    public Teacher test4(){
        return teacherService.findById(5);
    }
}
