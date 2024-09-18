package com.hyz.rpc.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.hyz.rpc.common.model.User;
import com.hyz.rpc.common.service.UserService;
import com.hyz.rpc.model.RpcRequest;
import com.hyz.rpc.model.RpcResponse;
import com.hyz.rpc.serializer.JdkSerializer;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户服务代理
 *
 * @author 何冠德
 * @date 2024/09/18
 */
@Slf4j
public class UserServiceProxy implements UserService {

    /**
     * 获取用户
     *
     * @param user 用户
     * @return {@link User }
     */
    @Override
    public User getUser(User user) {
        JdkSerializer serializer = new JdkSerializer();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();

        try {
            byte[] bytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://127.0.0.1:8080")
                    .body(bytes)
                    .execute()) {
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            log.error("静态代理失败",e);
        }
        return null;
    }
}
