package com.wugq.blog.controller;

import com.wugq.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/")
    public Object getTags(){

        return null;
    }


}
