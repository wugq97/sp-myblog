package com.wugq.blog.service.impl;

import com.wugq.blog.annotation.MyLog;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.dto.ArticleDto;
import com.wugq.blog.dto.ArticleFrontDto;
import com.wugq.blog.entity.Article;
import com.wugq.blog.entity.Category;
import com.wugq.blog.entity.User;
import com.wugq.blog.mapper.ArticleMapper;
import com.wugq.blog.service.*;
import com.wugq.blog.util.DateUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
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
    @Resource
    CommentService commentService;

    @MyLog(value = "新增文章", kind = "article", operator = "add")
    public int insert(Article article) {
        return articleMapper.insert(article);
    }

    @CachePut(value="article",key="#article.id")
    public Article update(Article article) {
         articleMapper.update(article);
         return article;
    }

    @Cacheable(value = "article",key = "#id",condition = "#id>0")
    public Article selectById(int id) {
        return articleMapper.selectById(id);
    }

    @MyLog(value = "删除文章", kind = "article", operator = "delete")
    @CacheEvict(value = "article", key = "#id")
    public int delete(int id) {
        return articleMapper.delete(id);
    }

    public void getByCondition(int uid, int parentCategoryId, int childCategoryId, PageInfo pageInfo) {
        List<Article> articles = articleMapper.selectByCondition(uid,parentCategoryId,childCategoryId,
                pageInfo.getStartIndex(),pageInfo.getPerPage());
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
            User user = userService.selectById(article.getUserId());
            if (user == null)
                dto.setUsername("");
            else
                dto.setUsername(user.getUsername());
            dto.setCategoryParentName((Optional.ofNullable(categoryService.selectById(article.getCategoryIdParent())).orElse(new Category()).getName()));
            dto.setCategoryChildName(Optional.ofNullable(categoryService.selectById(article.getCategoryIdChild())).orElse(new Category()).getName());
            String tagNames = Arrays.stream(article.getTagIds().split(","))
                    .filter(tagId -> !"".equals(tagId))
                    .map(tagId -> tagService.selectById(Integer.parseInt(tagId)))
                    .filter(tag -> tag!=null)
                    .map(tag -> tag.getName())
                    .collect(Collectors.joining(" "));
            dto.setTags(tagNames);
            dto.setStatus(article.getStatus());
            dtos.add(dto);
        });
        pageInfo.setItems(dtos);
    }

    @Override
    public int getNums() {
        return articleMapper.selectNums();
    }

    @Override
    public Date getLastUpdated() {
        return articleMapper.selectLastUpdated();
    }

    @Override
    public List<Article> getHotArticles() {
        List<Integer> articleIds = commentService.getHotArticleIds();
        return articleMapper.selectHot(articleIds);
    }

    @Override
    public void getFrontArticles(int category, int tag, PageInfo pageInfo) {
        List<Article> articles = articleMapper.selectFront(category,tag,pageInfo.getStartIndex(),pageInfo.getPerPage());
        pageInfo.setCount(articleMapper.selectCountFront(category,tag));
        List<ArticleFrontDto> dtos = new ArrayList<>();
        articles.forEach(article -> {
            ArticleFrontDto dto = new ArticleFrontDto();
            dto.setId(article.getId());
            dto.setTitle(article.getTitle());
            dto.setSubtitle(article.getSubtitle());
            dto.setViews(article.getViews());
            dto.setImg(article.getImg());
            dto.setTime(DateUtil.dateToString(article.getCreatedAt(),DateUtil.DEFAULT_DATE_FORMAT_CN));
            Category c = null;
            if(article.getCategoryIdChild()!=0){
                c = categoryService.selectById(article.getCategoryIdChild());
            } else if (article.getCategoryIdParent() != 0) {
                c = categoryService.selectById(article.getCategoryIdParent());
            } else {
                dto.setCategory("");
            }
            if(c!=null)
                dto.setCategory(c.getName());
            dtos.add(dto);
        });
        pageInfo.setItems(dtos);
    }

    @Override
    public List<Article> getSearchArticles(String text) {
        return articleMapper.selectSearch(text);
    }

    @Override
    public List<Article> getRecommend() {
        return articleMapper.selectRecommend();
    }

    @Override
    public Integer selectByCategory(int categoryId) {
        return articleMapper.selectByCategory(categoryId);
    }
}
