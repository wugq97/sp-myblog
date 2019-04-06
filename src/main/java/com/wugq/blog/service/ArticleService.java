package com.wugq.blog.service;

import com.wugq.blog.entity.Article;

public interface ArticleService {
    int insert(Article article);

    int update(Article article);

    Article selectById(int id);

    int delete(int id);
}
