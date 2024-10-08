package com.hyz.rpc.proxy;

import com.hyz.rpc.RpcApplication;
import java.lang.reflect.Proxy;

public class ServiceProxyFactory {

    /**
     * 获得代理
     *
     * @param serviceClass 服务类
     * @return {@link T }
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

    /**
     * 根据服务类获取 Mock 代理对象
     *
     * @param serviceClass 服务类
     * @return {@link T }
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy()
        );
    }
}
