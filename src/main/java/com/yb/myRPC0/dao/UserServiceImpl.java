package com.yb.myRPC0.dao;

import com.yb.myRPC0.pojo.User;

import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("客户端查询了： "+ id +"的用户");
        Random random = new Random();
        User user = User.builder().username(UUID.randomUUID().toString())
                .id(id)
                .sex(random.nextBoolean()).build();
        return user;
    }
}
