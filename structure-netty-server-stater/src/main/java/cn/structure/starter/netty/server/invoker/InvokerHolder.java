package cn.structure.starter.netty.server.invoker;


import java.util.HashMap;
import java.util.Map;

/**
 * 执行器操作类
 * @author Administrator
 */
public class InvokerHolder {

    /**
     * 用于存储执行器
     */
    public static Map<String, Map<String, SocketInvoker>> invokers = new HashMap();

    /**
     * 添加一个执行器
     *
     */
    public static void addInvoker(String module, String cmd, SocketInvoker invoker) {
        Map<String, SocketInvoker> map = invokers.get(module);
        if (map == null) {
            map = new HashMap();
            invokers.put(module, map);
        }
        map.put(cmd, invoker);
    }

    /**
     * 获取一个执行器
     *
     * @param module
     * @param cmd
     * @return
     */
    public static SocketInvoker getInvoker(String module, String cmd) {
        Map<String, SocketInvoker> map = invokers.get(module);
        if (map != null) {
            return map.get(cmd);
        }
        return null;
    }

    /**
     * 获取一个执行器
     * @param cmd
     * @return
     */
    public static SocketInvoker getInvoker(String cmd) {
        Map<String, SocketInvoker> map = invokers.get("default");
        if (map != null) {
            return map.get(cmd);
        }
        return null;
    }
}
