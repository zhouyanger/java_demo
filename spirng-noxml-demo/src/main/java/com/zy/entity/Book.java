package com.zy.entity;

import com.zy.Test;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@Data
@Component
public class Book {
    @Value("zhouyang")
    private String name;
}
