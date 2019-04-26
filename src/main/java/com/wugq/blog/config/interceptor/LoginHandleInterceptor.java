package com.wugq.blog.config.interceptor;

import com.wugq.blog.entity.User;
import com.wugq.blog.service.UserService;
import com.wugq.blog.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandleInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        Cookie tk = CookieUtil.getCookie(request,"tk");
        if(tk!=null){
          // 到Redis中找用户id
          int uid = 0;
          User user = userService.selectById(uid);
          request.setAttribute("user",user);
        }
        return true;
    }

}
