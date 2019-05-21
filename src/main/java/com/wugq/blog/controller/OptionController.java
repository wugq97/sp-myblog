package com.wugq.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionController {

    @GetMapping("/test")
    public Object test() {
        return 5555;
    }
}
