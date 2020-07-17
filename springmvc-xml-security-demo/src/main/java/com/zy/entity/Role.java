package com.zy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int id;
    private String name;
    private String descn;
    private List<User> users;
}
