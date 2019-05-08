package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.entity.User;
import com.wugq.blog.enums.ErrorCodeEnum;
import com.wugq.blog.enums.SuccessCodeEnum;
import com.wugq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Object login(String username, String password){
        User user = userService.validate(username,password);
        if(user!=null){
            String token = userService.setToken(user.getId());
            return new JsonResult(true, SuccessCodeEnum.LOGIN.getValue(),token);
        }else{
            return new JsonResult(false, ErrorCodeEnum.NOUSER.getValue(),ErrorCodeEnum.NOUSER.getDesc());
        }
    }

    @GetMapping("/info")
    public Object getInfo(@RequestParam("tk") String token){
        User user = userService.getByToken(token);
        if(user!=null)
            return new JsonResult(true,SuccessCodeEnum.INFO.getValue(),user);
        else
            return new JsonResult(true,ErrorCodeEnum.EXPIRE.getValue(),ErrorCodeEnum.EXPIRE.EXPIRE.getDesc());
    }

    @GetMapping("/logout")
    public Object logout(HttpServletRequest request){
        userService.deleteToken(request);
        return new JsonResult("重新登录");
    }
}
