package com.zy.init;

import com.zy.config.MyServletConfig;
import com.zy.config.MySpringConfig;
import com.zy.config.WebSecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import java.io.File;

/**
 * 替代web.xml配置，指定spring容器的配置类和springmvc容器的配置类
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 指定spring容器的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MySpringConfig.class, WebSecurityConfig.class};
    }

    /**
     * springmvc容器的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyServletConfig.class};
    }

    /**
     * 配置拦截请求路径
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
