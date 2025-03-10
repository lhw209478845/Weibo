package com.liu.weibocomment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.weibocomment.entity.User;

public interface UserService extends IService<User> {
    User login(String username, String password);

    void saveUserRole(Integer uid, Integer[] ids);
}
