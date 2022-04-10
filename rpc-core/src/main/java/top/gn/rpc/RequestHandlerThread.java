package top.gn.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gn.rpc.entity.RpcRequest;
import top.gn.rpc.entity.RpcResponse;
import top.gn.rpc.register.ServiceRegister;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandlerThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandlerThread.class);

    private Socket socket;
    private RequestHandler requestHandler;
    private ServiceRegister serviceRegister;

    public RequestHandlerThread(Socket socket, RequestHandler requestHandler, ServiceRegister serviceRegister) {
        this.socket = socket;
        this.requestHandler = requestHandler;
        this.serviceRegister = serviceRegister;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            String interfaceName = rpcRequest.getInterfaceName();
            Object service = serviceRegister.getService(interfaceName);
            Object result = requestHandler.handle(rpcRequest, service);
            objectOutputStream.writeObject(RpcResponse.success(result));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException  e) {
            logger.error("调用或发送时有错误发生：", e);
        }
    }
}
