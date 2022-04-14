package top.gn.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gn.rpc.entity.RpcRequest;
import top.gn.rpc.entity.RpcResponse;
import top.gn.rpc.register.ServiceProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandlerThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceProvider serviceProvider;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceProvider serviceProvider) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName = rpcRequest.getInterfaceName();
            Object result = requestHandler.handle(rpcRequest);
            objectOutputStream.writeObject(RpcResponse.success(result,rpcRequest.getRequestId()));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException  e) {
            logger.error("调用或发送时有错误发生：", e);
        }
    }
}
