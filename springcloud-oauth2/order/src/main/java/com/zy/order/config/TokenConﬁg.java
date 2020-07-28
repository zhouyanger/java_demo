package com.zy.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

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

    //使用jwt令牌就不用了
//    @Bean
//    public ResourceServerTokenServices tokenService() {
//        //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id，client_secret 
//        RemoteTokenServices service=new RemoteTokenServices();
//        service.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
//        service.setClientId("c1");
//        service.setClientSecret("123");
//        return service;
//    }
}
