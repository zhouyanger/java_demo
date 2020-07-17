package com.zy.configuration;

import com.zy.entity.Book;
import com.zy.entity.Student;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("db.properties")
@ComponentScan("com.zy")
@Import(SecondConfiguration.class)
public class SpringConfiguration {
    @Value("${name}")
    private String name;
    
}
