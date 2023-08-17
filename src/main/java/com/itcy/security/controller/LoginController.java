package com.itcy.security.controller;

import com.itcy.security.entity.User;
import com.itcy.security.module.ResponseResult;
import com.itcy.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: security
 * @BelongsPackage: com.itcy.security.controller
 * @Author: cuiYong
 * @CreateTime: 2023-08-15  15:27
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/logout")
    public ResponseResult logout(){

        return loginService.logout();
    }
}
