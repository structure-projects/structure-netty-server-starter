package cn.structure.starter.netty.server.configuration;

import cn.structure.starter.netty.server.filter.WebSocketServerInitialzer;
import cn.structure.starter.netty.server.properties.SocketProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.Log4JLoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * <p>
 * Socket
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/19 10:18
 */
public class Socket {


    private static  final InternalLogger log = Log4JLoggerFactory.getInstance(WebSocket.class);

    @Resource
    private SocketProperties socketProperties;

    @Resource
    private WebSocketServerInitialzer webSocketServerInitialzer;

    private EventLoopGroup mainGroup;

    private EventLoopGroup subGroup;

    private ChannelFuture future;

    @PostConstruct
    public void webSocketStart()throws Exception{
        mainGroup = new NioEventLoopGroup(socketProperties.getBossCount());
        subGroup = new NioEventLoopGroup(socketProperties.getWorkerCount());
        ServerBootstrap server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(webSocketServerInitialzer);
        //添加自定义初始化处理器
        future= server.bind(socketProperties.getPort()).sync();
        log.info("Socket start {} ", socketProperties.getPort());
    }

    @PreDestroy
    public void webSocketStop()throws Exception{
        future.channel().closeFuture().sync();
        mainGroup.shutdownGracefully();
        subGroup.shutdownGracefully();
    }
}
