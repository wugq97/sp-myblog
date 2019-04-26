package com.wugq.blog.config.interceptor;

import com.wugq.blog.entity.User;
import com.wugq.blog.enums.AuthEnum;
import com.wugq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        User user = userService.getLoginUser(request);
        if(user != null && user.getAuthority() != AuthEnum.NORMAL.getValue()){
            // 传回权限不足信息
            return true;
        }
        else{
            return false;
        }
    }
}
