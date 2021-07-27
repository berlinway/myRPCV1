package com.yb.myRPC5.server;


import com.yb.myRPC5.service.BlogService;
import com.yb.myRPC5.service.BlogServiceImpl;
import com.yb.myRPC5.service.UserService;
import com.yb.myRPC5.service.UserServiceImpl;

public class TestServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();

        ServiceProvider serviceProvider = new ServiceProvider("127.0.0.1",8899);
        serviceProvider.provideServiceInterface(userService);
        serviceProvider.provideServiceInterface(blogService);

        RPCServer RPCServer = new NettyRPCServer(serviceProvider);
        RPCServer.start(8899);
    }
}