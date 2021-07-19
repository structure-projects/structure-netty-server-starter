package cn.structure.starter.netty.server.annotations;

import cn.structure.starter.netty.server.configuration.Socket;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 * socker
 * </p>
 *
 * @author chuck
 * @version 1.0.1
 * @since 2021/7/19 9:56
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(Socket.class)
@Documented
@Inherited
public @interface EnableSocketServer {

    /**
     *
     * @return
     */
    Class<?> value() ;


}
