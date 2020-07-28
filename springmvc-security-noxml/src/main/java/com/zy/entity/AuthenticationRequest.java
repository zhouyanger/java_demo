package com.zy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor@Data
@Accessors(chain = true)
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}
