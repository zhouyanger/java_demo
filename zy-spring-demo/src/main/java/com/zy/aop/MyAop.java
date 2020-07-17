package com.zy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAop {
    @Pointcut(value = "execution(* *.say(..))")
    public void pointCut(){}
    @Before("pointCut()")
    public void befor(){
        System.out.println("我是aop的before");
    }
    @After("pointCut()")
    public void after(){
        System.out.println("我是aop的after");
    }
    @AfterReturning("pointCut()")
    public void afterReturn(){
        System.out.println("我是aop的afterReturn");
    }
    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void afterThrowing(Exception e){
        System.out.println("我是aop的afterThrowing");
    }
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("我是aop的环绕通知前置");
        Object proceed;
        try {
             proceed = proceedingJoinPoint.proceed();
            System.out.println("被代理类方法返回结果:"+proceed);
            System.out.println("我是aop的环绕通知后置");
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
