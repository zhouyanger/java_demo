package com.zy.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private TokenStore tokenStore;
//    @Autowired
//    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private AuthorizationCodeServices authorizationCodeServices;

    //客户端信息配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in-memory存储
                .withClient("c1") //客户端id
                .secret(new BCryptPasswordEncoder().encode("123"))  //客户端密钥
                .resourceIds("res1") //资源列表
                .authorizedGrantTypes( "password", "client_credentials", "refresh_token") //该client允许的授权类型
                .scopes("all")  // 允许的授权范围
                .autoApprove(false) //false跳转到授权页面
                .redirectUris("http://www.baidu.com");  //授权之后的跳转地址
    }
    //配置令牌服务
//    @Bean
//    public AuthorizationServerTokenServices authorizationServerTokenServices(){
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
////        defaultTokenServices.setClientDetailsService(clientDetailsService);
//        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setTokenStore(tokenStore);
//        defaultTokenServices.setAccessTokenValiditySeconds(7200);
//        defaultTokenServices.setRefreshTokenValiditySeconds(259000);
//        return defaultTokenServices;
//    }
    
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices(){
//        return new InMemoryAuthorizationCodeServices();
//    }
    //端点配置和token配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
//                .authorizationCodeServices(authorizationCodeServices)
//                .tokenServices(authorizationServerTokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET);
    }

    //端点安全配置
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
}
