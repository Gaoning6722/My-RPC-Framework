package top.gn.rpc.test;


import top.gn.rpc.api.HelloService;
import top.gn.rpc.server.RpcServer;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService,9000);
    }
}