package com.wugq.blog.mapper;

import com.wugq.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);

    int update(Comment comment);

    Comment selectById(int id);

    int delete(int id);
}
