package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/admin/comments")
    public Object getComments(int page,int pageSize) {
        PageInfo pageInfo = new PageInfo(page,pageSize);
        commentService.getComments(pageInfo);
        return new JsonResult(pageInfo);
    }

    @DeleteMapping("/admin/comments/{id}")
    public Object deleteComment(@PathVariable("id") int id) {
        commentService.delete(id);
        return new JsonResult(true);
    }
}
