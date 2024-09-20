package com.hyz.rpc;

import com.hyz.rpc.config.RpcConfig;
import com.hyz.rpc.constant.RpcConstant;
import com.hyz.rpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * rpc应用程序
 *
 * @author 何冠德
 * @date 2024/09/19
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 初始化
     *
     * @param newRpcConfig 新RPC配置
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc config init success, config = {}", newRpcConfig.toString());
    }

    /**
     * 初始化
     *
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 加载失败则使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取RPC配置
     *
     * @return {@link RpcConfig }
     */
    public static RpcConfig getRpcConfig() {
        if (ObjectUtils.isEmpty(rpcConfig)) {
            synchronized (RpcApplication.class) {
                if (ObjectUtils.isEmpty(rpcConfig)) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
