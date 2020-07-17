package com.zy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    private String name;
    private List<String> cards;
    private Student student;
}
