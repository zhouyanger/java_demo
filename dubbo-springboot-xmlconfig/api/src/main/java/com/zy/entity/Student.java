package com.zy.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Student implements Serializable {
    private int id;
    private String name;
    private int age;
}
