package top.gn.rpc.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gn.rpc.register.ServiceProvider;
import top.gn.rpc.RequestHandler;
import top.gn.rpc.RequestHandlerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class SocketServer {

    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private static final int corePoolSize = 5;
    private static final int maximumPoolSize = 50;
    private static final long keepAliveTime = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;

    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceProvider serviceProvider;

    public SocketServer(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.SECONDS,workingQueue,threadFactory);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动...");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接:{} : {} Ip为：", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket,requestHandler, serviceProvider));
            }
        } catch (IOException e) {
            logger.error("连接时有错误发生：", e);
        }
    }

}
