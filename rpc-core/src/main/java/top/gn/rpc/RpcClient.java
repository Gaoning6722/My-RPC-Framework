package top.gn.rpc;

import top.gn.rpc.entity.RpcRequest;
import top.gn.rpc.serializer.CommonSerializer;

public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
    void setSerializer(CommonSerializer serializer);
}
