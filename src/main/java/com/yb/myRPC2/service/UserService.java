package com.yb.myRPC2.service;

import com.yb.myRPC2.common.User;

public interface UserService {
    User getUserById(Integer id);
    Integer insertUserId(User user);

}
