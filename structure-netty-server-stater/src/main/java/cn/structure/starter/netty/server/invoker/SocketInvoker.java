package cn.structure.starter.netty.server.invoker;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 执行器
 * @author Administrator
 */
public class SocketInvoker {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 方法
     */
    private Method method;

    /**
     * 参数列表
     */
    private Map<String,String> args = new HashMap<>();

    /**
     * 初始化socket执行器
     */
    public static SocketInvoker valueOf(Object object, Method method) {
        SocketInvoker invoker = new SocketInvoker();
        invoker.setTarget(object);
        invoker.setMethod(method);
        return invoker;
    }

    /**
     * 执行参数为map的执行器
     **/
    public Object invoke(Map<String, Object> map) throws Throwable {
        Object obj = null;
        try {
            obj = method.invoke(target, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Throwable targetException = e.getTargetException();
            throw targetException;
        }
        return obj;
    }

    /**
     * 执行参数为 args 的执行器
     **/
     public Object invoke(Object... args) throws Throwable {
        Object obj = null;
        try {
            obj = method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            throw targetException;
        }
        return obj;
    }
    /**
     * 执行参数为 Object 的执行器
     **/
    public Object invoke(Object object) throws Throwable {
        Object obj = null;
        try {
        	if (object instanceof JSONObject ){
        		JSONObject jsonObject = (JSONObject)object;
				return invoke(jsonObject);
			}
            obj = method.invoke(target, object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            throw targetException;
        }
        return obj;
    }
    /**
     * 无参数
     **/
    public Object invoke() throws Throwable {
        Object obj = null;
        try {
            obj = method.invoke(target);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            throw targetException;
        }
        return obj;
    }

    /**
     * 执行参数为JSONObject
     * @param jsonObject
     * @return
     * @throws Throwable
     */
    public Object invoke(JSONObject jsonObject) throws Throwable {
        Object obj = null;
        try {
			Class<?>[] parameterTypes = method.getParameterTypes();
			Object object = null;
			for (Class clazz : parameterTypes){
				object = JSONObject.toJavaObject(jsonObject, clazz);
				continue;
			}
			obj = method.invoke(target,object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            throw targetException;
        }
        return obj;
    }

    /**
     * setTarget 方法
     **/
    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * setMethod 方法
     **/
    public void setMethod(Method method) {
        this.method = method;
    }

}
