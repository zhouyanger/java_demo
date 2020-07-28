package com.example.demozzzz.sms;


import com.example.demozzzz.controller.TestController;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
   *  短信验证码过滤器
   */

public class SmsCodeFilter extends OncePerRequestFilter {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
     private AuthenticationFailureHandler authenticationFailureHandler;

             private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

             public AuthenticationFailureHandler getAuthenticationFailureHandler() {
                 return authenticationFailureHandler;
             }

             public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
                 this.authenticationFailureHandler = authenticationFailureHandler;
             }

             public SessionStrategy getSessionStrategy() {
                 return sessionStrategy;
             }

             public void setSessionStrategy(SessionStrategy sessionStrategy) {
                 this.sessionStrategy = sessionStrategy;
             }

             @Override
     protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
                 if (httpServletRequest.getRequestURI().equals("/authentication/mobile") && httpServletRequest.getMethod().equals("POST")) {
                         try {
                                 validate(new ServletWebRequest(httpServletRequest));
                
                             }catch (AuthenticationException e){
                                 authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                                 return;
                             }
                     }
                 filterChain.doFilter(httpServletRequest,httpServletResponse);   //如果不是登录请求，直接调用后面的过滤器链
             }

             private void validate(ServletWebRequest request) throws ServletRequestBindingException {
                 ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, SESSION_KEY);
                 String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"smsCode");
                 if(!StringUtils.hasText(codeInRequest)){
                         throw new RuntimeException("验证码的值不能为空！");
                     }
                 if(codeInSession == null){
                         throw new RuntimeException("验证码不存在！");
                     }
                 if(codeInSession.isExpried()){
                         sessionStrategy.removeAttribute(request,TestController.SESSION_KEY);
                         throw new RuntimeException("验证码已过期！");
                     }
                 if(!codeInSession.getCode().equals(codeInRequest)){
                         throw new RuntimeException("验证码不正确！");
                     }
                 sessionStrategy.removeAttribute(request,TestController.SESSION_KEY);
             }
 }
