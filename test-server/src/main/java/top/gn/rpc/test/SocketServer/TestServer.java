package top.gn.rpc.test.SocketServer;


import top.gn.rpc.register.ServiceProviderImpl;
import top.gn.rpc.api.HelloService;
import top.gn.rpc.register.ServiceProvider;
import top.gn.rpc.socket.SocketServer;
import top.gn.rpc.test.HelloServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceProvider serviceProvider = new ServiceProviderImpl();
        serviceProvider.addServiceProvider(serviceProvider);
        SocketServer socketServer = new SocketServer(serviceProvider);
        socketServer.start(9000);
    }
}
