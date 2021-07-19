package cn.structure.example.netty.server;

import cn.structure.starter.netty.server.annotations.EnableSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * nettyServerStart
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/19 11:45
 */
@EnableSocketServer(value = SocketHandler.class)
@SpringBootApplication
public class NettyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class,args);
    }
}
