package com.yb.myRPC2.client;

import com.yb.myRPC2.common.User;
import com.yb.myRPC2.service.UserService;
import com.yb.myRPC2.common.Blog;
import com.yb.myRPC2.service.BlogService;

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

        System.out.println("#######################################");
        BlogService blogService = clientProxy.getProxy(BlogService.class);
        Blog blog =  blogService.getBlogById(10000);
        System.out.println("get blog id from server: "+ blog);
    }


}
