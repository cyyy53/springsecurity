package com.itcy.security.handler;

import com.alibaba.fastjson.JSON;
import com.itcy.security.module.ResponseResult;
import com.itcy.security.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: security
 * @BelongsPackage: com.itcy.security.handler
 * @Author: cuiYong
 * @CreateTime: 2023-08-17  09:33
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(),"您的权限不足");
        String string = JSON.toJSONString(result);
        WebUtils.renderString(response,string);
    }
}
