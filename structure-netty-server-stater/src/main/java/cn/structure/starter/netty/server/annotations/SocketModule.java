package cn.structure.starter.netty.server.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface SocketModule {

    /**
     * socket 指令模块编码 默认可以为空
     */
    String module() default "default";

}
