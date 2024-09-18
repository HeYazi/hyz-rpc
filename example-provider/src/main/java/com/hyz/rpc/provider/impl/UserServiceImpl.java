package com.hyz.rpc.provider.impl;

import com.hyz.rpc.common.model.User;
import com.hyz.rpc.common.service.UserService;

/**
 * 用户服务导入
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public class UserServiceImpl implements UserService{

    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
