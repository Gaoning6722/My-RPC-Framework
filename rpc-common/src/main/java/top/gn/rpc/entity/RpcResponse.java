package top.gn.rpc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.gn.rpc.enumeration.ResponseCode;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RpcResponse<T> implements Serializable {
    /**
     * 响应对应的请求号
     */
    private String requestId;
    /**
     * 响应状态码
     */
    private Integer statusCode;

    /**
     * 相应状态的补充信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }

}
