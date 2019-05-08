package com.wugq.blog.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.wugq.blog.common.JsonResult;
import com.wugq.blog.entity.User;
import com.wugq.blog.enums.AuthEnum;
import com.wugq.blog.enums.ErrorCodeEnum;
import com.wugq.blog.service.UserService;
import com.wugq.blog.util.WebApplicationUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminLoginHandlerInterceptor implements HandlerInterceptor {

    private UserService userService;

    public AdminLoginHandlerInterceptor(){
        userService = WebApplicationUtil.getBean(UserService.class);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        User user = userService.getLoginUser(request);
        if(user != null && user.getAuthority() != AuthEnum.NORMAL.getValue()){
            return true;
        }
        else{
            response.reset();
            response.addHeader("Access-Control-Allow-Origin","*");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = null;
            try{
                pw = response.getWriter();
                String result = JSON.toJSONString(new JsonResult(true, ErrorCodeEnum.AUTHORITY.getValue(), ErrorCodeEnum.AUTHORITY.getDesc()));
                pw.write(result);
            }catch(IOException e){

            }finally {
                pw.flush();
                pw.close();
            }
            return false;
        }
    }
}
