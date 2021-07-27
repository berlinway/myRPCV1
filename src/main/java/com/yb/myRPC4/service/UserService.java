package com.yb.myRPC4.service;

import com.yb.myRPC4.common.User;

public interface UserService {
    User getUserById(Integer id);
    Integer insertUserId(User user);

}
