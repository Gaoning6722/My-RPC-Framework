package top.gn.rpc.test.SocketClient;


import top.gn.rpc.RpcClient;
import top.gn.rpc.api.HelloObject;
import top.gn.rpc.api.HelloService;
import top.gn.rpc.RpcClientProxy;
import top.gn.rpc.socket.SocketClient;

public class TestClient {
    public static void main(String[] args) {
        RpcClient client = new SocketClient();
        for (int i = 0; i < 5; i++) {
            RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
            HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
            HelloObject object = new HelloObject(12, "This is a message");
            String res = helloService.hello(object);
            System.out.println(res);
        }
    }
}
