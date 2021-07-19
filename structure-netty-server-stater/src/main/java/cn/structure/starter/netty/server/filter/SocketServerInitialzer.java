package cn.structure.starter.netty.server.filter;

import cn.structure.starter.netty.server.configuration.SocketNettyConfig;
import cn.structure.starter.netty.server.properties.SocketProperties;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * <p>
 * socketServer初始化
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/19 10:16
 */
public class SocketServerInitialzer extends ChannelInitializer<SocketChannel> {

    public SocketServerInitialzer() {
    }

    public SocketServerInitialzer(SocketNettyConfig socketNettyConfig, SocketProperties socketProperties) {
        this.socketNettyConfig = socketNettyConfig;
        this.socketProperties = socketProperties;
    }

    private SocketNettyConfig socketNettyConfig;

    private SocketProperties socketProperties;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", socketNettyConfig.getSocketChannelHandler());
    }
}
