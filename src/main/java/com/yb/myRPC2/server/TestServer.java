package com.yb.myRPC2.server;

import com.yb.myRPC2.service.BlogService;
import com.yb.myRPC2.service.UserService;

import java.util.HashMap;
import java.util.Map;

public class TestServer {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BlogService blogService = new BlogServiceImpl();
        ServiceProvider serviceProvide = new ServiceProvider();
        serviceProvide.provideServiceInterface(userService);
        serviceProvide.provideServiceInterface(blogService);

//        Map<String,Object> serviceProvide = new HashMap<>();
//        serviceProvide.put("com.yb.myRPC2.service.UserService",userService);
//        serviceProvide.put("com.yb.myRPC2.service.BlogService",blogService);

        RPCServer rpcServer = new SimpleRPCServer(serviceProvide);
        rpcServer.start(8899);
    }

}
