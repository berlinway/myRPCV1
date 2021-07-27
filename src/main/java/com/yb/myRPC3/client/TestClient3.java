package com.yb.myRPC3.client;

import com.yb.myRPC3.common.User;
import com.yb.myRPC3.service.UserService;

public class TestClient3 {

    public static void main(String[] args) {
        BIORPCClient biorpcClient = new BIORPCClient("127.0.0.1",8899);
        ClientProxy rpcProxy = new ClientProxy(biorpcClient);
        UserService userService = rpcProxy.getProxy(UserService.class);
        User user = userService.getUserById(1000);
        System.out.println("get user from server: "+user);
    }
}
