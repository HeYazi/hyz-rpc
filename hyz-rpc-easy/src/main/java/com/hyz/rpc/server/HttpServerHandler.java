package com.hyz.rpc.server;

import com.hyz.rpc.model.RpcRequest;
import com.hyz.rpc.model.RpcResponse;
import com.hyz.rpc.registry.LocalRegistry;
import com.hyz.rpc.serializer.JdkSerializer;
import com.hyz.rpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

/**
 * HTTP服务器处理程序
 *
 * @author 何冠德
 * @date 2024/09/18
 */
@Slf4j
public class HttpServerHandler implements Handler<HttpServerRequest> {


    /**
     * 处理
     *
     * @param request 请求
     */
    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();
        // 记录日志
        log.info("请求内容，请求类型：{}，uri：{}", request.method(), request.uri());
        // 异步处理 HTTP 请求
        request.bodyHandler(body->{
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                log.error("反序列化失败",e);
                throw new RuntimeException(e);
            }
            RpcResponse rpcResponse = new RpcResponse();
            if (ObjectUtils.isEmpty(rpcRequest)) {
                log.error("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }

            try {
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getClass());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                log.error("调用失败",e);
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse, serializer);
        });
    }

    private void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response().putHeader("content-type", "application/json");
        byte[] bytes = null;
        try {
            bytes = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(bytes));
        } catch (IOException e) {
            log.error("序列化失败",e);
            throw new RuntimeException(e);
        }
    }
}
