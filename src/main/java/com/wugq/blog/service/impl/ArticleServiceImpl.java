package com.wugq.blog.service.impl;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.dto.ArticleDto;
import com.wugq.blog.entity.Article;
import com.wugq.blog.entity.Category;
import com.wugq.blog.mapper.ArticleMapper;
import com.wugq.blog.service.ArticleService;
import com.wugq.blog.service.CategoryService;
import com.wugq.blog.service.TagService;
import com.wugq.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    ArticleMapper articleMapper;
    @Resource
    CategoryService categoryService;
    @Resource
    TagService tagService;
    @Resource
    UserService userService;

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

    public void getByCondition(int uid, int parentCategoryId, int childCategoryId, PageInfo pageInfo) {
        List<Article> articles = articleMapper.selectByCondition(uid,parentCategoryId,childCategoryId,
                pageInfo.getStartIndex(),pageInfo.getCount());
        pageInfo.setCount(articleMapper.selectCountByCondition(uid,parentCategoryId,childCategoryId));
        List<ArticleDto> dtos = new ArrayList<>();
        articles.forEach(article -> {
            ArticleDto dto = new ArticleDto();
            dto.setId(article.getId());
            dto.setUserId(article.getUserId());
            dto.setTitle(article.getTitle());
            dto.setSubTitle(article.getSubtitle());
            dto.setContent(article.getContent());
            dto.setCategoryIdParent(article.getCategoryIdParent());
            dto.setGetCategoryIdChild(article.getCategoryIdChild());
            dto.setTagIds(article.getTagIds());
            dto.setImg(article.getImg());
            dto.setViews(article.getViews());
            dto.setLikes(article.getLikes());
            dto.setCreatedAt(article.getCreatedAt());
            dto.setUpdatedAt(article.getUpdatedAt());
            dto.setUsername(userService.selectById(article.getUserId()).getUsername());
            dto.setCategoryParentName((Optional.ofNullable(categoryService.selectById(article.getCategoryIdParent())).orElse(new Category()).getName()));
            dto.setCategoryChildName(Optional.ofNullable(categoryService.selectById(article.getCategoryIdChild())).orElse(new Category()).getName());
            String tagNames = Arrays.stream(article.getTagIds().split(","))
                    .filter(tagId -> !"".equals(tagId))
                    .map(tagId -> tagService.selectById(Integer.parseInt(tagId)))
                    .map(tag -> tag.getName())
                    .collect(Collectors.joining(" "));
            dto.setTags(tagNames);
            dtos.add(dto);
        });
        pageInfo.setItems(dtos);
    }
}
