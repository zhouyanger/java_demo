package com.zy.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.experimental.Accessors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    private Integer id;
    private String name;
    private String password;
    private int status;  //0不可用 1可用
    private List<String> roles;

}
