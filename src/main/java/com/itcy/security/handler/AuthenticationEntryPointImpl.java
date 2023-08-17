package com.itcy.security.handler;

import com.alibaba.fastjson.JSON;
import com.itcy.security.module.ResponseResult;
import com.itcy.security.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: security
 * @BelongsPackage: com.itcy.security.handler
 * @Author: cuiYong
 * @CreateTime: 2023-08-17  09:25
 * @Description: TODO
 * @Version: 1.0
 * 登录失败异常
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"用户名或密码错误");
        String string = JSON.toJSONString(result);

            //处理异常
        WebUtils.renderString(response,string);
    }
}
