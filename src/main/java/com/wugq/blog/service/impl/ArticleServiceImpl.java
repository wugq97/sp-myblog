package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Article;
import com.wugq.blog.mapper.ArticleMapper;
import com.wugq.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;

    public int insert(Article article) {
        return articleMapper.insert(article);
    }

    public int update(Article article) {
        return articleMapper.update(article);
    }

    public Article selectById(int id) {
        return articleMapper.selectById(id);
    }

    public int delete(int id) {
        return articleMapper.delete(id);
    }
}
