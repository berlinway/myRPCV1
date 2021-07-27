package com.yb.myRPC3.server;

import com.yb.myRPC3.client.RPCClient;
import com.yb.myRPC3.service.BlogService;
import com.yb.myRPC3.service.UserService;

public class TestNettyServer {

    public static void main(String[] args) {
        ServiceProvider serviceProvider = new ServiceProvider();
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);
        RPCServer rpcServer =  new NettyRPCServer(serviceProvider);
        rpcServer.start(8899);
    }

}
