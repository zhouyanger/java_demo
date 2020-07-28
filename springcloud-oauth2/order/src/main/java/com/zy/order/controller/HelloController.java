package com.zy.order.controller;

import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/admin/hello")
    @PreAuthorize("hasRole('admin')")
    public Object admin() {
        return "hello admin";
    }
    
    @GetMapping("/user/hello")
    @PreAuthorize("hasRole('user')")
    public String user() {
        return "hello user";
    }

    @GetMapping("/info/getCurrentUser")
    public Object info(Authentication authentication) {
        OAuth2Authentication principal = (OAuth2Authentication) authentication.getPrincipal();
        OAuth2AuthenticationDetails detail = (OAuth2AuthenticationDetails) principal.getPrincipal();
        String tokenValue = detail.getTokenValue();
        return "token:"+tokenValue+"         username:"+principal.getName();
    }
}