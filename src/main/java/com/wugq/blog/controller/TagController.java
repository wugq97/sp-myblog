package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Tag;
import com.wugq.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    public Object getTags(Integer page,Integer pageSize){
        PageInfo pageInfo = new PageInfo(page,pageSize);
        tagService.getTags(pageInfo);
        return new JsonResult(true,0,pageInfo);
    }

    @PostMapping("/tags")
    public Object addTag(Tag tag){
        tagService.insert(tag);
        return new JsonResult(true);
    }

    @GetMapping("tags/{id}")
    public Object getTag(@PathVariable("id") Integer id){
        Tag tag = tagService.selectById(id);
        return new JsonResult(tag);
    }

    @PutMapping("/tags")
    public Object updateTag(Tag tag){
        tagService.update(tag);
        return new JsonResult(true);
    }

    @DeleteMapping("tags/{id}")
    public Object deleteTag(@PathVariable("id") Integer id){
        tagService.delete(id);
        return new JsonResult(true);
    }
}
