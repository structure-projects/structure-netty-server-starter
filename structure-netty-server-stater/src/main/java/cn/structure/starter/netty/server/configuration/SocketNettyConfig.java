package cn.structure.starter.netty.server.configuration;

import cn.structure.starter.netty.server.annotations.EnableSocketServer;
import cn.structure.starter.netty.server.annotations.EnableWebSocketServer;
import io.netty.channel.ChannelHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

/**
 *
 * Socket netty 配置
 */
@Configuration
public class SocketNettyConfig  implements ApplicationListener<ContextRefreshedEvent> {

	@Qualifier(value = "webSocketChannelHandler")
	private ChannelHandler webSocketChannelHandler;

	@Qualifier(value = "socketChannelHandler")
	private ChannelHandler socketChannelHandler;

	public ChannelHandler getWebSocketChannelHandler() {
		return webSocketChannelHandler;
	}

	public void setWebSocketChannelHandler(ChannelHandler webSocketChannelHandler) {
		this.webSocketChannelHandler = webSocketChannelHandler;
	}

	public ChannelHandler getSocketChannelHandler() {
		return socketChannelHandler;
	}

	public void setSocketChannelHandler(ChannelHandler socketChannelHandler) {
		this.socketChannelHandler = socketChannelHandler;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
		Map<String, Object> webSocketMap = event.getApplicationContext().getBeansWithAnnotation(EnableWebSocketServer.class);
		for (Object object : webSocketMap.values()) {
			Class<?> clazz = object.getClass();
			EnableWebSocketServer enableWebSocketServer = clazz.getAnnotation(EnableWebSocketServer.class);
			Class value = enableWebSocketServer.value();
			Object obj = value.newInstance();
			webSocketChannelHandler = (ChannelHandler)obj;
			continue;
		}

		Map<String, Object> socketServerMap = event.getApplicationContext().getBeansWithAnnotation(EnableSocketServer.class);
		for (Object object : socketServerMap.values()) {
			Class<?> clazz = object.getClass();
			EnableSocketServer enableSocketServer = clazz.getAnnotation(EnableSocketServer.class);
			Class value = enableSocketServer.value();
			Object obj = value.newInstance();
			socketChannelHandler = (ChannelHandler)obj;
			continue;
		}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
