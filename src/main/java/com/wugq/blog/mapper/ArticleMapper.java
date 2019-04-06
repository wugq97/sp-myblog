package com.wugq.blog.mapper;

import com.wugq.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    int insert(Article article);

    int update(Article article);

    Article selectById(int id);

    int delete(int id);
}
