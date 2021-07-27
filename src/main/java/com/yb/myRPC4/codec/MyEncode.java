package com.yb.myRPC4.codec;

import com.yb.myRPC4.common.RPCRepose;
import com.yb.myRPC4.common.RPCRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyEncode extends MessageToByteEncoder {

    private Serializer serializer;
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        System.out.println(o.getClass());
        //写入消息类型
        if(o instanceof RPCRequest){
            byteBuf.writeShort(MessageType.REQUEST.getCode());
        }
        else if(o instanceof RPCRepose){
            byteBuf.writeShort(MessageType.RESPONSE.getCode());
        }
        //写入序列化的方式
        byteBuf.writeShort(serializer.getType());
        //get serlize arr
        byte[] serialize = serializer.serialize(o);
        //write byte length
        byteBuf.writeInt(serialize.length);
        //write  byte arr
        byteBuf.writeBytes(serialize);
    }
}
