package com.hyz.rpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册表
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public class LocalRegistry {

    /**
     * 注册信息存储
     */
    private static final Map<String, Class<?>> MAP = new ConcurrentHashMap<String, Class<?>>();

    /**
     * 注册
     *
     * @param serviceName 服务名称
     * @param implClass impl类
     */
    public static void register(String serviceName, Class<?> implClass) {
        MAP.put(serviceName, implClass);
    }

    public static Class<?> get(String serviceName) {
        return MAP.get(serviceName);
    }


    /**
     * 删除
     *
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        MAP.remove(serviceName);
    }
}
