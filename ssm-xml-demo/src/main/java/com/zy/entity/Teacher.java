package com.zy.entity;

import java.util.List;

import lombok.Data;

@Data
public class Teacher {
    private Integer id;
    private String name;
    private List<Student> students;
}
