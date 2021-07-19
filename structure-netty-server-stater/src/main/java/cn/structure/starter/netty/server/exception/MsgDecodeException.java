package cn.structure.starter.netty.server.exception;


import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.Log4JLoggerFactory;

/***
 * 消息解包错误
 */
public class MsgDecodeException extends RuntimeException {

    private static  final InternalLogger log = Log4JLoggerFactory.getInstance(MsgDecodeException.class);

    public MsgDecodeException(ExceptionCode code, Exception e) {
        super();
        log.error("|错误ID：{}|错误摘要：{}|错误消息：{}|->", code.getCode(), code.getMsg(), e.getMessage());
    }
}
