package cn.structure.starter.netty.server.exception;


public enum ExceptionCode {

    SOCKETERROR(10001, "Socket通信未知错误"),
    SOCKETDECODER(10002, "Socket通信解码错误"),
    SOCKETENCODER(10003, "Socket通信编码错误"),
    CREATEROOERROR(10011, "创建房间失败！"),
    ;


    private int code;

    private String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
