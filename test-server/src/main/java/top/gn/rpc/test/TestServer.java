package top.gn.rpc.test;


import top.gn.rpc.register.DefaultServiceRegistry;
import top.gn.rpc.api.HelloService;
import top.gn.rpc.register.ServiceRegister;
import top.gn.rpc.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegister serviceRegister = new DefaultServiceRegistry();
        serviceRegister.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegister);
        rpcServer.start(9000);
    }
}
