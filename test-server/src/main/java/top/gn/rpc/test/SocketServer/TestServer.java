package top.gn.rpc.test.SocketServer;


import top.gn.rpc.register.DefaultServiceRegistry;
import top.gn.rpc.api.HelloService;
import top.gn.rpc.register.ServiceRegister;
import top.gn.rpc.socket.SocketServer;
import top.gn.rpc.test.HelloServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegister serviceRegister = new DefaultServiceRegistry();
        serviceRegister.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegister);
        socketServer.start(9000);
    }
}
