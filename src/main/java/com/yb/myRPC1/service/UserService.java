package com.yb.myRPC1.service;

import com.yb.myRPC1.common.User;

public interface UserService {
    User getUserById(Integer id);
    Integer insertUserId(User user);

}
