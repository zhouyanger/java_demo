package com.zy.auu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
public class TokenConﬁg {
    private String SIGNING_KEY = "uaa123";
    //配置令牌存储方式
    @Bean
    public TokenStore tokenStore(){
//        return new InMemoryTokenStore(); //使用内存
        return new JwtTokenStore(accessTokenConverter());
    }
    @Bean 
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(SIGNING_KEY);
        return jwtAccessTokenConverter;
    }

    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service=new DefaultTokenServices(); //config中设置的内存客户端详情服务
        service.setSupportRefreshToken(true);  //支持刷新令牌
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter()));
        service.setTokenEnhancer(tokenEnhancerChain);
        service.setTokenStore(tokenStore());
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时 
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天 
        return service;
    }
}
