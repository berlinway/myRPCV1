package com.yb.myRPC4.server;

import com.yb.myRPC4.common.User;
import com.yb.myRPC4.service.UserService;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl  implements UserService {
    @Override
    public User getUserById(Integer id) {
        Random random = new Random();
        User user = User.builder().userName(UUID.randomUUID().toString()).id(id).sex(random.nextBoolean()).build();
        System.out.println(user);
        return user;
    }

    @Override
    public Integer insertUserId(User user) {
        System.out.println("insert user : "+user);
        return 1;
    }
}
