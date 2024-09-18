package com.hyz.rpc.consumer;


import com.hyz.rpc.config.RpcConfig;
import com.hyz.rpc.utils.ConfigUtils;

public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }

}
