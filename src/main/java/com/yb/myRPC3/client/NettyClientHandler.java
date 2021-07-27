package com.yb.myRPC3.client;

import com.yb.myRPC3.common.RPCRepose;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

public class NettyClientHandler extends SimpleChannelInboundHandler<RPCRepose> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRepose rpcRepose) throws Exception {
        AttributeKey<RPCRepose> key = AttributeKey.valueOf("RPCRepose");
        channelHandlerContext.channel().attr(key).set(rpcRepose);
        channelHandlerContext.channel().close();
    }
}
