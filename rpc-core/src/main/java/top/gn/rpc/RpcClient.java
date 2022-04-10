package top.gn.rpc;

import top.gn.rpc.entity.RpcRequest;

public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}
