package com.wugq.blog.service;

import com.wugq.blog.entity.Comment;

public interface CommentService {

    int insert(Comment comment);

    int update(Comment comment);

    Comment selectById(int id);

    int delete(int id);
}
