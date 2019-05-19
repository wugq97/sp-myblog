package com.wugq.blog.service;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.dto.CommentDto;
import com.wugq.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    int insert(Comment comment);

    int update(Comment comment);

    Comment selectById(int id);

    int delete(int id);

    int getNums();

    List<Integer> getHotArticleIds();

    List<CommentDto> getCommentsByArticle(int articleId);

    void getComments(PageInfo pageInfo);

    void deleteByArticle(int articleId);
}
