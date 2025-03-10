package com.liu.weibocomment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weibocomment.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username = #{username} and password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);
}
