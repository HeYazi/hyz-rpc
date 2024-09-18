package com.hyz.rpc.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * rpc反应
 *
 * @author 何冠德
 * @date 2024/09/18
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse implements Serializable {

    /** 数据 */
    private Object data;
    /** 数据类型 */
    private Class<?> dataType;
    /** 消息 */
    private String message;
    /** 异常 */
    private Exception exception;
}
