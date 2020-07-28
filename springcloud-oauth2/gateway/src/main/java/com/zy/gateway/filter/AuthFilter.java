package com.zy.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zy.gateway.common.EncryptUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null){
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody("没有认证");
            return null;
        }
        if (!(authentication instanceof OAuth2Authentication)){
            //没token访问网关资源放行
            return null;
        }
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)authentication;
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();  //oauth中封装的请求信息
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        String username = userAuthentication.getName();  //用户名
        Collection<? extends GrantedAuthority> oauthorities = userAuthentication.getAuthorities(); //权限信息
        List<String> authorities = new ArrayList<>();
        oauthorities.stream().forEach(a -> authorities.add(((GrantedAuthority)a).getAuthority()));
        Map<String ,Object> jsonToken = new HashMap<>();
       //组装明文token，转发给微服务，放入header，名称为json‐token
        jsonToken.put("principal",username);
        jsonToken.put("authorities",authorities);
        jsonToken.put("oAuth2Request",oAuth2Request);
        //加入zuul的header中
        currentContext.addZuulRequestHeader("json-token", EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonToken)));
        return null;
    }
}
