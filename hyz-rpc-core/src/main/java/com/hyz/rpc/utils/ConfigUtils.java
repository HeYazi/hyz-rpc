package com.hyz.rpc.utils;

import cn.hutool.setting.dialect.Props;
import org.apache.commons.lang3.StringUtils;

/**
 * 配置工具类
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public class ConfigUtils {


    /**
     * 加载配置
     *
     * @param tClass t类
     * @param prefix 前缀
     * @return {@link T }
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }


    /**
     * 加载配置
     *
     * @param tClass t类
     * @param prefix 前缀
     * @param environment 环境
     * @return {@link T }
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StringUtils.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }
}
