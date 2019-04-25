package com.wugq.blog.controller;

import com.wugq.blog.common.SimpleJsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public Object login(){
        return new SimpleJsonResult(true);
    }

}
