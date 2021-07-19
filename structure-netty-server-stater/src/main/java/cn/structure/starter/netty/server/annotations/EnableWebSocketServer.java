package cn.structure.starter.netty.server.annotations;


import cn.structure.starter.netty.server.configuration.WebSocket;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(WebSocket.class)
@Documented
@Inherited
public @interface EnableWebSocketServer {
	/**
	 *
	 * @return
	 */
	Class<?> value() ;
}
