package cn.structure.starter.netty.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * webSocket配置
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/19 10:25
 */
@Configuration
@ConfigurationProperties(value = "structure.netty.socket.web")
public class WebSocketProperties {

    private int bossCount = 1;

    private int workerCount = 1 ;

    private String uri = "/ws";

    private int port = 18001;

    private int maxContentLength = 1024 * 64 ;


    public int getBossCount() {
        return bossCount;
    }

    public void setBossCount(int bossCount) {
        this.bossCount = bossCount;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxContentLength() {
        return maxContentLength;
    }

    public void setMaxContentLength(int maxContentLength) {
        this.maxContentLength = maxContentLength;
    }
}
