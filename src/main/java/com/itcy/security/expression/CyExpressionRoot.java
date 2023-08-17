package com.itcy.security.expression;

import com.itcy.security.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @BelongsProject: security
 * @BelongsPackage: com.itcy.security.expression
 * @Author: cuiYong
 * @CreateTime: 2023-08-17  15:58
 * @Description: TODO
 * @Version: 1.0
 */
@Component("ex")
public class CyExpressionRoot {

    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        //权限去重
        Set<String> permissionsSet = permissions.stream().collect(Collectors.toSet());
        //判断用户权限集合中是否存在authority
        return permissionsSet.contains(authority);
    }
}
