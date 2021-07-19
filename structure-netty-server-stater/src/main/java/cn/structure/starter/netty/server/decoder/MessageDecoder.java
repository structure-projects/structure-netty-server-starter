package cn.structure.starter.netty.server.decoder;

import cn.structure.starter.netty.server.entity.SocketMsg;
import cn.structure.starter.netty.server.exception.ExceptionCode;
import cn.structure.starter.netty.server.exception.MsgDecodeException;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 解码器
 */
public class MessageDecoder extends ByteToMessageDecoder {
    //protostuff 的写法
    private Schema<SocketMsg> schema = RuntimeSchema.getSchema(SocketMsg.class);

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> obj) throws Exception {
        try {
            //定义接收消息的数组
            byte[] msg = new byte[in.readableBytes()];

            //读取消息
            in.readBytes(msg);

            //定义消息头
            byte[] dataLen = new byte[4];

            //拆分消息头
            System.arraycopy(msg, 0, dataLen, 0, 4);

            //通过消息头取得数据大小
            int len = CoderUtil.bytesToInt(dataLen, 0);

            //定义数据数组
            byte[] data = new byte[len];

            //取得数据
            System.arraycopy(msg, 4, data, 0, len);

            //创建实例化对象
            SocketMsg message = new SocketMsg();

            //将数据进行实例化

            ProtobufIOUtil.mergeFrom(data, message, schema);

            //返回实例化对象
            obj.add(message);

        } catch (MsgDecodeException e) {
            //如发生异常记录异常日志
            throw new MsgDecodeException(ExceptionCode.SOCKETDECODER, e);

        }
    }
}
