package com.zy.config;

import com.zy.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
   * @EnableWebMvc 注解会开启一些默认配置，如：ViewResolver MessageConverter 等,等同于<mvc:annotation-driven></>
   * 若无此注解，重写 WebMvcConfigurerAdapter 方法无效，等同
 2 * */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zy.controller")
public class WebConfiguration implements WebMvcConfigurer {
    
    //配置jsp view
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        resolver.setOrder(2);//设置优先级
        resolver.setCache(false);
        return resolver;
    } 
    
    //  配置静态资源的处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //配置静态资源访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/statics/**").addResourceLocations("/statics/");
    }
    //一些简单的controller处理，直接返回相应的界面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("hello");
    }
    
    //吧自定义的拦截器加入容器
    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }
    //添加上面配置好的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(myInterceptor());
    }
    
}
