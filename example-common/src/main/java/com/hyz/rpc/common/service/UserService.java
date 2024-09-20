package com.hyz.rpc.common.service;

import com.hyz.rpc.common.model.User;

/**
 * 用户服务
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param user 用户
     * @return {@link User }
     */
    User getUser(User user);

    default short getNumber() {
        return 1;
    }
}
