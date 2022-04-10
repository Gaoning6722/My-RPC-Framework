package top.gn.rpc.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.gn.rpc.RequestHandler;
import top.gn.rpc.entity.RpcRequest;
import top.gn.rpc.entity.RpcResponse;
import top.gn.rpc.register.DefaultServiceRegistry;
import top.gn.rpc.register.ServiceRegister;

public class NettyServerHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    private static RequestHandler requestHandler;
    private static ServiceRegister serviceRegister;

    static {
        requestHandler = new RequestHandler();
        serviceRegister = new DefaultServiceRegistry();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        try {
            logger.info("服务器接收到请求: {}", msg);
            String interfaceName = msg.getInterfaceName();
            Object service = serviceRegister.getService(interfaceName);
            Object result = requestHandler.handle(msg, service);
            ChannelFuture future = ctx.writeAndFlush(RpcResponse.success(result));
            future.addListener(ChannelFutureListener.CLOSE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("处理过程调用时有错误发生：");
        cause.printStackTrace();
        ctx.close();
    }
}
