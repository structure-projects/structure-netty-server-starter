package cn.structure.starter.netty.server.filter;

import cn.structure.starter.netty.server.configuration.SocketNettyConfig;
import cn.structure.starter.netty.server.properties.WebSocketProperties;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 初始化webSocket
 * */
public class WebSocketServerInitialzer extends ChannelInitializer<SocketChannel> {

	private SocketNettyConfig socketNettyConfig;

	private WebSocketProperties webSocketProperties;

	public WebSocketServerInitialzer() {

	}

	public WebSocketServerInitialzer(SocketNettyConfig socketNettyConfig, WebSocketProperties webSocketProperties) {
		this.socketNettyConfig = socketNettyConfig;
		this.webSocketProperties = webSocketProperties;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		//webSocket基于http协议，所以需要http编解码器
		pipeline.addLast(new HttpServerCodec());
		//添加对于读写大数据流的支持
		pipeline.addLast(new ChunkedWriteHandler());
		//对httpMessage进行聚合
		pipeline.addLast(new HttpObjectAggregator(webSocketProperties.getMaxContentLength()));
		// ================= 上述是用于支持http协议的 ==============
		//webSocket 服务器处理的协议，用于给指定的客户端进行连接访问的路由地址
		//比如处理一些握手动作(ping,pong)
		pipeline.addLast(new WebSocketServerProtocolHandler(webSocketProperties.getUri()));
		//自定义handler
		pipeline.addLast(socketNettyConfig.getWebSocketChannelHandler());
	}
}
