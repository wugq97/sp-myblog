package com.wugq.blog.service;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Article;

import java.util.Date;
import java.util.List;

public interface ArticleService {
    int insert(Article article);

    int update(Article article);

    Article selectById(int id);

    int delete(int id);

    void getByCondition(int uid, int parentCategoryId, int childCategoryId, PageInfo pageInfo);

    int getNums();

    Date getLastUpdated();

    List<Article> getHotArticles();

    void getFrontArticles(int category, int tag, PageInfo pageInfo);

    List<Article> getSearchArticles(String text);

    List<Article> getRecommend();
}
