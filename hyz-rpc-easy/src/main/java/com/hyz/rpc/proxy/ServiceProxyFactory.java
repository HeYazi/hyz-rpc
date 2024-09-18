package com.hyz.rpc.proxy;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {

    /**
     * 获得代理
     *
     * @param serviceClass 服务类
     * @return {@link T }
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
