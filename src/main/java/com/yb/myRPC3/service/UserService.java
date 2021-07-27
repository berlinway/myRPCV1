package com.yb.myRPC3.service;

import com.yb.myRPC3.common.User;

public interface UserService {
    User getUserById(Integer id);
    Integer insertUserId(User user);

}
