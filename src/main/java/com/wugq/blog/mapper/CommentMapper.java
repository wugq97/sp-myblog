package com.wugq.blog.mapper;

import com.wugq.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);

    int update(Comment comment);

    Comment selectById(int id);

    int delete(int id);

    int selectNums();

    List<Integer> selectHotArticleIds();

    List<Comment> selectByArticle(@Param("articleId") int articleId,@Param("isParent") int isParent);

    List<Comment> select(@Param("start") int start,@Param("count") int count);

    void deleteByArticle(@Param("articleId") int articleId);
}
