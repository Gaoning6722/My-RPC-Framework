package top.gn.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 实现serializable接口，因为为在调用过程中从客户端传输到服务端
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;
    private String message;
}
