package com.zy.service;

import com.zy.dao.UserMapper;
import com.zy.entity.Role;
import com.zy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserDetailsService{
    @Autowired
    private UserMapper userMapper;

    /**
     * 认证数据库信息业务
     * @param s 浏览器穿进来的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userByName = userMapper.findUserByName(s);
        if (userByName==null){
            //返回null，就表示认证失败
            return null;
        }
        //返回userDetail的实现累User也可以。
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<Role> roles = userByName.getRoles();
                List<GrantedAuthority> list  = new ArrayList<>();
                if(!roles.isEmpty()){
                    for (Role role:roles){
                        list.add(new SimpleGrantedAuthority(role.getName()));
                    }
                    
                }
                
                
                return list;
            }

            @Override
            public String getPassword() {
                return userByName.getPassword();
            }

            @Override
            public String getUsername() {
                return userByName.getName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return userByName.getStatus()==1;
            }
        };
       return userDetails;
    }
}
