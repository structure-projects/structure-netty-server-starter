package cn.structure.starter.netty.server.annotations;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SocketCommand {

    /**
     * socket:指令编码
     */
    String command();
}
