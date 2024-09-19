package com.hyz.rpc.config;

import com.hyz.rpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * rpc配置
 *
 * @author 何冠德
 * @date 2024/09/18
 */
@Data
public class RpcConfig {

    /** 名字 */
    private String name = "hyz-rpc";

    /** 版本 */
    private String version = "1.0";

    /** 服务器主机 */
    private String serverHost = "127.0.0.1";

    /** 服务器端口 */
    private Integer serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;

    /** 序列化器 */
    private String serializer = SerializerKeys.JDK;
}
