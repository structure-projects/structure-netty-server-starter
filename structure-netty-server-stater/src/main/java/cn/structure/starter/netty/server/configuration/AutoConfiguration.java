package cn.structure.starter.netty.server.configuration;

import cn.structure.starter.netty.server.filter.SocketServerInitialzer;
import cn.structure.starter.netty.server.filter.WebSocketServerInitialzer;
import cn.structure.starter.netty.server.properties.SocketProperties;
import cn.structure.starter.netty.server.properties.WebSocketProperties;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>
 * 自动装配类
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/19 10:11
 */
@Configuration
@EnableConfigurationProperties(value = {SocketProperties.class,WebSocketProperties.class})
public class AutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(WebSocketServerInitialzer.class)
    public WebSocketServerInitialzer webSocketServerInitialzer(){
        return new WebSocketServerInitialzer(socketNettyConfig,webSocketProperties);
    }

    @Bean
    @ConditionalOnMissingBean(SocketServerInitialzer.class)
    public SocketServerInitialzer socketServerInitialzer(){
        return new SocketServerInitialzer(socketNettyConfig,socketProperties);
    }

    @Resource
    private SocketNettyConfig socketNettyConfig;

    @Resource
    private WebSocketProperties webSocketProperties;

    @Resource
    private SocketProperties socketProperties;

}
