package top.gn.rpc.test.NettyClient;

import top.gn.rpc.RpcClientProxy;
import top.gn.rpc.api.HelloObject;
import top.gn.rpc.api.HelloService;
import top.gn.rpc.netty.NettyClient;

public class NettyTestClient {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 9999);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(nettyClient);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(13, "This is a message");
        String hello = helloService.hello(object);
        System.out.println(hello);
    }
}
