package com.yb.myRPC3.client;

import com.yb.myRPC3.common.User;
import com.yb.myRPC3.service.UserService;

public class TestNettyClient {
    public static void main(String[] args) {
        RPCClient nettyRPCClient = new NettyRPCClient("127.0.0.1",8899);
        ClientProxy clientProxy = new ClientProxy(nettyRPCClient);
        UserService userService = clientProxy.getProxy(UserService.class);
        User user = userService.getUserById(100);
        System.out.println(user);
    }
}
