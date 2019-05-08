package com.wugq.blog.config.interceptor;

import com.wugq.blog.entity.User;
import com.wugq.blog.service.UserService;
import com.wugq.blog.util.WebApplicationUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandleInterceptor implements HandlerInterceptor {

    private UserService userService;

    public LoginHandleInterceptor(){
        userService = WebApplicationUtil.getBean(UserService.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String tk = request.getParameter("tk");
        if(tk!=null){
          User user = userService.getByToken(tk);
          request.setAttribute("user",user);
        }
        return true;
    }

}
