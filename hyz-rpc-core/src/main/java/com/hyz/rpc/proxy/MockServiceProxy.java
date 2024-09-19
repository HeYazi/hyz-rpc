package com.hyz.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

/**
 * Mock 服务代理
 *
 * @author heguande
 * @date 2024/09/19
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {

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
        Class<?> returnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(returnType);
    }

    /**
     * 生成指定类型的默认值对象
     *
     * @param type 返回体类型
     * @return 返回体默认值
     */
    private Object getDefaultObject(Class<?> type) {
        if (type.isPrimitive()) {
            if (type == boolean.class) {
                return false;
            }
            if (type == short.class) {
                return (short) 0;
            }
            if (type == int.class) {
                return 0;
            }
            if (type == long.class) {
                return 0L;
            }
        }
        return null;
    }
}
