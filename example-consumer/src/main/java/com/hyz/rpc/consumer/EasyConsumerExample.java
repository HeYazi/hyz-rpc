package com.hyz.rpc.consumer;

import com.hyz.rpc.common.model.User;
import com.hyz.rpc.common.service.UserService;
import com.hyz.rpc.proxy.ServiceProxyFactory;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 简单消费者示例
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        // 静态代理
//        UserService userService = new UserServiceProxy();
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("hyz");
        User result = userService.getUser(user);
        if (ObjectUtils.isEmpty(result)) {
            System.out.println("user == null");
            return;
        }
        System.out.println(result.getName());
    }
}