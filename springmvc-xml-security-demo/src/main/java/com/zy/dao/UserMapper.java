package com.zy.dao;

import com.zy.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public User findUserByName(@Param("name") String name);
    
}
