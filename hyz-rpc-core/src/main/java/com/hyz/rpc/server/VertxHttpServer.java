package com.hyz.rpc.server;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * 顶点HTTP服务器
 *
 * @author 何冠德
 * @date 2024/09/18
 */
@Slf4j
public class VertxHttpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 创建vert.x实例
        Vertx vertx = Vertx.vertx();
        // 创建 http 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        // 监听端口并处理请求
        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务并且监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                log.info("开始监听，port：{}", port);
            } else {
                log.error("监听失败");
            }
        });
    }
}
