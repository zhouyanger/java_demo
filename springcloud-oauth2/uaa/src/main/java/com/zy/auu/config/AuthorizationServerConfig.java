package com.zy.auu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;
    // 该对象用来将令牌信息存储到内存中
    @Autowired
    AuthorizationServerTokenServices tokenService;

    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    
    // 该对象用来将令牌信息存储到Redis中
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.inMemory()  // 使用in-memory存储
                .withClient("c1")
                .secret(new BCryptPasswordEncoder().encode("123")) //使用加密
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token") //授权模式
                .accessTokenValiditySeconds(1800) // 配置access_token的过期时间
                .resourceIds("res1") //配置资源id
                .autoApprove(false) //是否自动授权
                .scopes("all")
                 .redirectUris("http://www.baidu.com");
    }

   

    //授权码模式
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }
    //配置端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager) //密码模式
                .authorizationCodeServices(authorizationCodeServices) //授权码模式
                .tokenServices(tokenService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 表示支持表单验证
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")  //获取jwt公钥，验证token
                .checkTokenAccess("permitAll()") //检查token
                .allowFormAuthenticationForClients();
    }
}
