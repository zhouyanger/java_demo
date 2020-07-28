package com.example.demozzzz.sms;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;   //过期时间

    public ValidateCode(String code, Integer expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }
    
    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
