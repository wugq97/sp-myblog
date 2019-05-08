package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.User;
import com.wugq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Object getUsers(Integer authority,Integer currentPage,Integer pageSize){
        PageInfo pageInfo = new PageInfo(currentPage,pageSize);
        userService.getUsers(authority,pageInfo);
        return new JsonResult(pageInfo);
    }

    @DeleteMapping("/users/{id}")
    public Object deleteUser(@PathVariable("id") Integer id){
        userService.delete(id);
        return new JsonResult(true);
    }

    @PutMapping("/users/{id}")
    public Object updateAuth(@PathVariable("id") Integer id,@RequestParam("auth") Integer auth){
        User user = userService.selectById(id);
        user.setAuthority(auth);
        userService.update(user);
        return new JsonResult(true);
    }
}
