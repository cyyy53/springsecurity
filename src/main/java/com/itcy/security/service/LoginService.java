package com.itcy.security.service;

import com.itcy.security.entity.User;
import com.itcy.security.module.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();

}
