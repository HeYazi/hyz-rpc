package com.hyz.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.hyz.rpc.model.RpcRequest;
import com.hyz.rpc.model.RpcResponse;
import com.hyz.rpc.serializer.JdkSerializer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务代理
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public class ServiceProxy implements InvocationHandler {

    private static final Logger log = LoggerFactory.getLogger(ServiceProxy.class);

    /**
     * 调用代理
     *
     * @param proxy 代理
     * @param method 方法
     * @param args arg游戏
     * @return {@link Object }
     * @throws Throwable throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        JdkSerializer serializer = new JdkSerializer();

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder().serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        byte[] bytes = serializer.serialize(rpcRequest);

        // todo heguande 地址硬编码，后续需要修改为注册中心和服务发现机制解决
        // 发送请求
        try (HttpResponse httpResponse = HttpRequest.post("http://127.0.0.1:8080")
                .body(bytes)
                .execute()) {
            byte[] result = httpResponse.bodyBytes();
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return rpcResponse.getData();
        } catch (Exception e) {
            log.error("动态代理失败",e);
        }
        return null;
    }
}
