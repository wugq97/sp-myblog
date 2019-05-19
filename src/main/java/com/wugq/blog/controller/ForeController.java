package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.dto.ArticleFrontDto;
import com.wugq.blog.dto.CommentDto;
import com.wugq.blog.entity.Article;
import com.wugq.blog.entity.Comment;
import com.wugq.blog.entity.Tag;
import com.wugq.blog.entity.User;
import com.wugq.blog.service.*;
import com.wugq.blog.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/front")
public class ForeController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;
    @Autowired
    ViewService viewService;
    @Autowired
    UserService userService;

    @GetMapping("info")
    public Object getInfo() {
        Map<String,Object> result = new HashMap<>();
        result.put("article",articleService.getNums());
        result.put("category",categoryService.getNums());
        result.put("comment",commentService.getNums());
        result.put("tag",tagService.getNums());
        result.put("time", DateUtil.dateToString(articleService.getLastUpdated(),DateUtil.DEFAULT_DATE_FORMAT_CN));
        return new JsonResult(result);
    }

    @GetMapping("hotArticles")
    public Object getHotArticles() {
        List<Article> articleList = articleService.getHotArticles();
        return new JsonResult(articleList);
    }

    @GetMapping("tags")
    public Object getTags() {
        List<Tag> tags = tagService.getAll();
        return new JsonResult(tags);
    }

    @GetMapping("articles")
    public Object getArticles(@RequestParam(value = "category",defaultValue = "0") int category,
                              @RequestParam(value = "tag",defaultValue = "0") int tag,
                              @RequestParam(value = "page",defaultValue = "1") int page,
                              @RequestParam(value = "pageSize",defaultValue = "7") int pageSize) {
        PageInfo<ArticleFrontDto> pageInfo = new PageInfo(page,pageSize);
        articleService.getFrontArticles(category,tag,pageInfo);
        return new JsonResult(pageInfo);
    }

    @GetMapping("search")
    public Object getSearchArticles(String text){
        List<Article> articles = articleService.getSearchArticles(text);
        return new JsonResult(articles);
    }

    @GetMapping("article/{id}")
    public Object getArticle(@PathVariable("id") int id) {
        Article article = articleService.selectById(id);
        return new JsonResult(article);
    }

    @PostMapping("comment")
    public Object addComment(Comment comment) {
        commentService.insert(comment);
        return new JsonResult(true);
    }

    @GetMapping("comment")
    public Object getComments(int articleId) {
        List<CommentDto> comments = commentService.getCommentsByArticle(articleId);
        return new JsonResult(comments);
    }

    @PostMapping("article/view")
    public Object addViews(int articleId) {
        Article article = articleService.selectById(articleId);
        article.setViews(article.getViews() + 1);
        articleService.update(article);
        return new JsonResult(true);
    }

    @GetMapping("like")
    public Object getView(int articleId,int userId){
        int num = viewService.getView(articleId,userId);
        return new JsonResult(num);
    }

    @PostMapping("article/like")
    public Object addLike(int articleId,int userId) {
        Article article = articleService.selectById(articleId);
        article.setLikes(article.getLikes() + 1);
        articleService.update(article);
        viewService.addView(articleId,userId);
        return new JsonResult(true);
    }

    @GetMapping("recommend")
    public Object getRecommend() {
        List<Article> articles = articleService.getRecommend();
        return new JsonResult(articles);
    }

    @PostMapping("user/username")
    public Object updateUsername(int id,String username) {
        User user = userService.selectById(id);
        user.setAccount(username);
        user.setUsername(username);
        userService.update(user);
        return new JsonResult(true);
    }

    @PostMapping("user/password")
    public Object updatePassword(int id,String oldPassword,String newPassword) {
        int num = userService.updatePassword(id,oldPassword,newPassword);
        return new JsonResult(num);
    }

    @GetMapping("article/category")
    public Object getCategoryByArticle(int id) {
        Article article = articleService.selectById(id);
        Map<String,Object> result = new HashMap<>();
        result.put("name",article.getTitle());
        result.put("parent",categoryService.selectById(article.getCategoryIdParent()));
        result.put("child",categoryService.selectById(article.getCategoryIdChild()));
        return new JsonResult(result);
    }
}
