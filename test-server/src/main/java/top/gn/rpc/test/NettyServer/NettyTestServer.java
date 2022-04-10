package top.gn.rpc.test.NettyServer;

import top.gn.rpc.api.HelloService;
import top.gn.rpc.netty.NettyServer;
import top.gn.rpc.register.DefaultServiceRegistry;
import top.gn.rpc.register.ServiceRegister;
import top.gn.rpc.test.HelloServiceImpl;

public class NettyTestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegister serviceRegister = new DefaultServiceRegistry();
        serviceRegister.register(helloService);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(9999);
    }
}
