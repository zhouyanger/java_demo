package com.zy.annotion;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Data
@Component
public class Category {
    @Resource(name = "book")
    private Book book;
}
