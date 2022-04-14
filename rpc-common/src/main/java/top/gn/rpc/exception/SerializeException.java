package top.gn.rpc.exception;

import top.gn.rpc.enumeration.RpcError;

public class SerializeException extends RuntimeException {
    public SerializeException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }
    public SerializeException(String message, Throwable cause) {
        super(message, cause);
    }
    public SerializeException(String detail) {
        super(detail);
    }
}
