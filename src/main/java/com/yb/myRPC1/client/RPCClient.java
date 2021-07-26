package com.yb.myRPC1.client;

import com.yb.myRPC1.common.User;
import com.yb.myRPC1.service.UserService;

import java.net.Socket;

public class RPCClient {

    public static void main(String[] args) {
//        Socket socket = new Socket("127.0.0.1",8899);
        ClientProxy clientProxy = new ClientProxy("127.0.0.1",8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        //method1
        User user = proxy.getUserById(10);
        System.out.println("user from server: "+ user);

        //method2
        User user1 = User.builder().userName("zhangsan").id(100).sex(true).build();
        Integer i = proxy.insertUserId(user1);
        System.out.println("insert num : "+i);
    }


}
