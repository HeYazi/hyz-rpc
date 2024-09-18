package com.hyz.rpc.serializer;

import java.io.IOException;

/**
 * 序列化器
 *
 * @author 何冠德
 * @date 2024/09/18
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param obj obj
     * @return {@link byte[] }
     * @throws IOException
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     *
     * @param data 数据
     * @param clazz clazz
     * @return {@link T }
     * @throws IOException ioexception
     */
    <T> T deserialize(byte[] data, Class<T> clazz) throws IOException;
}
