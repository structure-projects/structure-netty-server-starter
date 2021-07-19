package cn.structure.starter.netty.server.decoder;

import cn.structure.starter.netty.server.entity.SocketMsg;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 编码器
 */
@ChannelHandler.Sharable
public class MessageEncoder extends MessageToByteEncoder<SocketMsg> {

    private Schema<SocketMsg> schema = RuntimeSchema.getSchema(SocketMsg.class);

    /**
     * 传入协议数据，产生携带包头之后的数据
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, SocketMsg message, ByteBuf out) throws Exception {

        //定义数据长度
        LinkedBuffer buffer = LinkedBuffer.allocate(1024);
        //将实例对象反序列化成字节数组
        byte[] data = ProtobufIOUtil.toByteArray(message, schema, buffer);
        //在写消息之前需要把消息的长度添加到头4个字节
        ByteBuf buf = Unpooled.copiedBuffer(CoderUtil.intToBytes(data.length), data);
        out.writeBytes(buf);
    }
}