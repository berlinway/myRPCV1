package com.yb.myRPC3.server;

import com.yb.myRPC3.common.RPCRepose;
import com.yb.myRPC3.common.RPCRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@AllArgsConstructor
public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {
    private ServiceProvider serviceProvider;


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest request) throws Exception {
        RPCRepose repose = getResponse(request);
        channelHandlerContext.writeAndFlush(repose);
        channelHandlerContext.close();
    }

    private RPCRepose getResponse(RPCRequest request) {
        //得到服务名
        String interfaceName = request.getIntefaceName();
        //得到服务端相应服务实现类
        Object service = serviceProvider.getService(interfaceName);
        Method method = null;
        try {
            method = service.getClass().getMethod(request.getMethodName(),request.getParamsTypes());
            Object invoke = method.invoke(service,request.getParams());
            return RPCRepose.success(invoke);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }finally {
            return RPCRepose.fail("fail");
        }
    }
}
