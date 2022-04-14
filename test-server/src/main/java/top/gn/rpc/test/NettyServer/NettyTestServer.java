package top.gn.rpc.test.NettyServer;

import top.gn.rpc.api.HelloService;
import top.gn.rpc.netty.NettyServer;
import top.gn.rpc.serializer.ProtobufSerializer;
import top.gn.rpc.test.HelloServiceImpl;

public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new ProtobufSerializer());
        server.publishService(helloService, HelloService.class);
    }
}
