package top.gn.rpc;

import top.gn.rpc.serializer.CommonSerializer;

public interface RpcServer {
    void start();
    void setSerializer(CommonSerializer serializer);
    <T> void publishService(Object service, Class<T> serviceClass);
}
