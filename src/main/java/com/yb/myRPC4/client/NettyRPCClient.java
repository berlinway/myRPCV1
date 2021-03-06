package com.yb.myRPC4.client;

import com.yb.myRPC4.common.RPCRepose;
import com.yb.myRPC4.common.RPCRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;


public class NettyRPCClient implements RPCClient {
    private static final Bootstrap bootstrap;
    private static final EventLoopGroup eventLoopGroup;
    private String host;
    private int port;

    public NettyRPCClient(String host,int port){
        this.host = host;
        this.port = port;
    }
    static {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler((new NettyClientInitializer()));
    }

    /**
     * 这里需要操作一下，因为netty的传输都是异步的，你发送request，会立刻返回， 而不是想要的相应的response
     * @param request
     * @return
     */
    @Override
    public RPCRepose sendRequest(RPCRequest request) {
        try {
            ChannelFuture channelFuture = bootstrap.connect(host,port).sync();
            Channel channel = channelFuture.channel();
            //发送数据
            channel.writeAndFlush(request);
            channel.closeFuture().sync();
            // 阻塞的获得结果，通过给channel设计别名，获取特定名字下的channel中的内容（这个在hanlder中设置）
            // AttributeKey是，线程隔离的，不会由线程安全问题。
            // 实际上不应通过阻塞，可通过回调函数
            AttributeKey<RPCRepose> key = AttributeKey.valueOf("RPCRepose");
            RPCRepose repose = channel.attr(key).get();
            System.out.println(repose);
            return repose;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
