package com.zy.auu.sms;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 短信验证码的过滤器
 */
public class SmsCodeFilter extends OncePerRequestFilter {
    private static final String SESSION_KEY_SMS="user_mobile_code";
    //获取session工具类
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletRequest.getSession();
        //拦截登陆请求
        if("/authentication/form".equalsIgnoreCase(httpServletRequest.getRequestURI())
                &&"post".equalsIgnoreCase(httpServletRequest.getMethod())){
            try {
                validate(httpServletRequest);
                //放行
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }

    /**
     * 校验验证码

     */
    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getSession();
        //拿出session中的Code对象
        String sessionCode = (String)session.getAttribute(SESSION_KEY_SMS);
        //拿出请求中的验证码
        String smsCode = request.getParameter("smsCode").trim();
        //校验
        if("".equalsIgnoreCase(smsCode)){
            throw new RuntimeException("验证码不能为空");
        }
        if(sessionCode == null){
            throw new RuntimeException("验证码不存在，请刷新验证码");
        }
        if(!smsCode.equalsIgnoreCase(sessionCode)){
            throw new RuntimeException("验证码错误");
        }
        //验证通过，移除session中验证码
        session.removeAttribute(SESSION_KEY_SMS);
    }

}
