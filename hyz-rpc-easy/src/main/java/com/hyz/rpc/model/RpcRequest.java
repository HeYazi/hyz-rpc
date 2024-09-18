package com.hyz.rpc.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rpc请求
 *
 * @author 何冠德
 * @date 2024/09/18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {

    /** 服务名称 */
    private String serviceName;
    /** 方法名称 */
    private String methodName;
    /** 参数类型 */
    private Class<?>[] parameterTypes;
    /** 参数 */
    private Object[] args;


}
