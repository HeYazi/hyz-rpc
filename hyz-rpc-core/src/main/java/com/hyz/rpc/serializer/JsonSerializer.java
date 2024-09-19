package com.hyz.rpc.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyz.rpc.model.RpcRequest;
import com.hyz.rpc.model.RpcResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonSerializer implements Serializer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T obj) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) throws IOException {
        T obj = OBJECT_MAPPER.readValue(data, clazz);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj, clazz);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj, clazz);
        }
        return obj;
    }

    /**
     * 处理请求
     *
     * @param rpcRequest rpc请求
     * @param type 类型
     * @return {@link T }
     * @throws IOException ioexception
     */
    private <T> T handleRequest(RpcRequest rpcRequest, Class<T> type) throws IOException {
        Class<?>[] parameterTypes = rpcRequest.getParameterTypes();
        Object[] args = rpcRequest.getArgs();

        // 循环处理每个参数的类型
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> clazz = parameterTypes[i];
            // 如果类型不同，则重新处理一下类型
            if (!clazz.isAssignableFrom(args[i].getClass())) {
                byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(bytes, clazz);
            }
        }
        return type.cast(rpcRequest);
    }

    /**
     * 处理响应
     *
     * @param rpcResponse rpc反应
     * @param type 类型
     * @return {@link T }
     * @throws IOException ioexception
     */
    private <T> T handleResponse(RpcResponse rpcResponse, Class<T> type) throws IOException {
        // 处理响应数据
        byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(rpcResponse.getData());
        rpcResponse.setData(OBJECT_MAPPER.readValue(bytes, rpcResponse.getDataType()));
        return type.cast(rpcResponse);
    }
}
