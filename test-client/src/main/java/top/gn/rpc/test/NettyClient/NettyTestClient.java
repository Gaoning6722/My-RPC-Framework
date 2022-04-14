package top.gn.rpc.test.NettyClient;

import top.gn.rpc.RpcClient;
import top.gn.rpc.RpcClientProxy;
import top.gn.rpc.api.HelloObject;
import top.gn.rpc.api.HelloService;
import top.gn.rpc.netty.NettyClient;
import top.gn.rpc.serializer.ProtobufSerializer;

public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient();
        client.setSerializer(new ProtobufSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(13, "This is a message");
        String hello = helloService.hello(object);
        System.out.println(hello);
    }
}
