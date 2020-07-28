package com.zy.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MyDispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // root配置类初始化
    // 指定 Root WebApplicationContext 类，这个类必须@Configuration来注解，从而代替XML配置文件
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringCnfiguration.class};
    }
    // web配置类初始化
    // 指定 Servlet WebApplicationContext 类，这个类必须@Configuration来注解，从而代替XML配置文件
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }
// 映射根路径初始化
    // 指定 Servlet mappings
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //配置字符过滤器
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(false);
        return new Filter[]{characterEncodingFilter};
    }

}
