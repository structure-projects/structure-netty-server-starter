package cn.structure.starter.netty.server.entity;


import java.io.Serializable;

/**
 * socket消息通信类
 */
public class SocketMsg<T> implements Serializable {

    /* 消息区域码 */
    private int module;

    /* 指令 */
    private int command;

    /* 消息 */
    private T message;

    public SocketMsg() {
    }

    public SocketMsg(int module, int command) {
        this.module = module;
        this.command = command;
    }

    public SocketMsg(int module, int command, T message) {
        this.module = module;
        this.command = command;
        this.message = message;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
