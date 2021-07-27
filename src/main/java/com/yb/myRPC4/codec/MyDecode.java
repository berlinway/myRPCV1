package com.yb.myRPC4.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MyDecode extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //1、read meaasge type
        short messageType = byteBuf.readShort();
        // 只支持request 和response请求
        if(messageType!=MessageType.REQUEST.getCode() &&
           messageType!=MessageType.RESPONSE.getCode()  ){
            System.out.println("invalid type");
            return;
        }
        //2、 read serializer type
        short serializerType = byteBuf.readShort();
        // get serializer
        Serializer serializer = Serializer.getSerializerByCode(serializerType);
        if(serializer==null) throw new RuntimeException("the serializer is not exist");
        //3. serializer bytes length
        int length = byteBuf.readInt();
        //4.read byte array
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        //use serilizer to decode bytes array
        Object deserialize = serializer.deserialize(bytes,messageType);
        list.add(deserialize);
    }
}
