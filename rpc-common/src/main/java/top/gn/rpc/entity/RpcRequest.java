package top.gn.rpc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class RpcRequest implements Serializable {
    public RpcRequest() {
    }

    /**
     * 待调用接口的名称
     */
    private String interfaceName;
    /**
     * 待调用方法的名称
     */
    private String methodName;
    /**
     * 待调用方法的参数
     */
    private Object[] parameters;
    /**
     * 待调用方法的参数类型
     */
    private Class<?>[] paramTypes;
}
