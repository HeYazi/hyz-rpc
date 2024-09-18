package com.hyz.rpc.provider;

import com.hyz.rpc.RpcApplication;
import com.hyz.rpc.common.service.UserService;
import com.hyz.rpc.provider.impl.UserServiceImpl;
import com.hyz.rpc.registry.LocalRegistry;
import com.hyz.rpc.server.HttpServer;
import com.hyz.rpc.server.VertxHttpServer;

/**
 * 简单提供者示例
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();
        // 服务注册
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
