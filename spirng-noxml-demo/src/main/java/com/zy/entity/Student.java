package com.zy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student {
    private Book book;
    public Student(Book book){
        this.book = book;
    }
}
