package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Comment;
import com.wugq.blog.mapper.CommentMapper;
import com.wugq.blog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    public int update(Comment comment) {
        return commentMapper.update(comment);
    }

    public Comment selectById(int id) {
        return commentMapper.selectById(id);
    }

    public int delete(int id) {
        return commentMapper.delete(id);
    }
}
