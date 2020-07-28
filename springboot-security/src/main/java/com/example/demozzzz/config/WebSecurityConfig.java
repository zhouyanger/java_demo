package com.example.demozzzz.config;

import com.example.demozzzz.sms.SmsCodeAuthenticationSecurityConfig;
import com.example.demozzzz.sms.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService myUserDetailService;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Bean
    public PasswordEncoder passwordencoder(){
        //BCryptPasswordEncoder implements PasswordEncoder
        return NoOpPasswordEncoder.getInstance();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin() //表单认证 
                .loginPage("/login.html") //登录页面
//                .loginProcessingUrl("/login")  //自定义登陆请求url
                .loginProcessingUrl("/authentication/mobile") //指定表单提交的地址用于替换UsernamePasswordAuthenticationFilter默认的提交地址
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .successForwardUrl("/login-success")  //登陆成功后的url
                .and()
                .authorizeRequests() //下边的都是授权的配置
                .antMatchers("/a/**").hasRole("admin")  //a请求必须有admin角色
                .antMatchers("/u/**").hasRole("user")   //a请求必须有admin角色
                .antMatchers("/login.html","/code/sms").permitAll() //放过登录页不过滤，否则报错
                .anyRequest()        //任何请求
                .authenticated()    //都需要身份认证
                 .and()
                .csrf().disable()// 关闭CSRF跨域
                .apply(smsCodeAuthenticationSecurityConfig);;
    }
}