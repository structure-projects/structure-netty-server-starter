package cn.structure.starter.netty.server.invoker;


import cn.structure.starter.netty.server.annotations.SocketCommand;
import cn.structure.starter.netty.server.annotations.SocketModule;
import cn.structure.starter.netty.server.exception.InvokerRescanException;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.Log4JLoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 执行扫描器
 * @author Administrator
 */

@Component
public class ScannerApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static  final InternalLogger log = Log4JLoggerFactory.getInstance(ScannerApplicationListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info(" Invoker Scan StartIng ……  ");
        //获取有SocketModule标签的类
        Map<String, Object> objectMap = event.getApplicationContext().getBeansWithAnnotation(SocketModule.class);
        //遍历带有SocketModule标签的类
        for (Object object : objectMap.values()) {
            //将object 转换位class
            Class<?> clazz = object.getClass();
            //获取SocketModule 标签用户取属性
            SocketModule socketModule = clazz.getAnnotation(SocketModule.class);
            Method[] methods = clazz.getMethods();
            //判断方法长度是否可以遍历
            if (methods != null && methods.length > 0) {
                //遍历方法
                for (Method method : methods) {
                    //获取执行
                    SocketCommand socketCmd = method.getAnnotation(SocketCommand.class);
                    //判断如果没有标签跳出此次循环继续遍历
                    if (socketCmd == null) {
                        continue;
                    }
                    //获取模块
                    String module = socketModule.module();
                    //获取指令
                    String cmd = socketCmd.command();
                    //初始化执行器
                    SocketInvoker invoker = SocketInvoker.valueOf(object, method);
                    //判断执行器是否为空
                    if (InvokerHolder.getInvoker(module, cmd) == null) {
                        //添加执行器
                        InvokerHolder.addInvoker(module, cmd, invoker);
                        log.info("addInvoker >> module: {}, cmd: {}",module,cmd);
                    } else {
                        throw new InvokerRescanException(module + "|" + cmd);
                    }
                }
            }
        }
    }
}
