package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Article;
import com.wugq.blog.service.ArticleService;
import com.wugq.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/admin/articles")
    public Object getArticles(int uid, int page, int pageSize,
                              @RequestParam(value="parentCategoryId",defaultValue = "0") int parentCategoryId,
                              @RequestParam(value="childCategoryId",defaultValue = "0") int childCategoryId){
        PageInfo pageInfo = new PageInfo(page,pageSize);
        articleService.getByCondition(uid,parentCategoryId,childCategoryId,pageInfo);
        Map<String,Object> results = new HashMap();
        results.put("pageInfo",pageInfo);
        results.put("categories",categoryService.getAll());
        return new JsonResult(true,0,results);
    }

    @GetMapping("/admin/articles/{id}")
    public Object getArticle(@PathVariable("id") int id){
        Article article = articleService.selectById(id);
        return new JsonResult(article);
    }

    @PostMapping("/admin/articles")
    public Object addArticle(Article article) {
        if("".equals(article.getImg())){
            String img = "/image/random/img_" + (int)(400*Math.random()) + ".jpg";
            article.setImg(img);
        }
        articleService.insert(article);
        return new JsonResult(true);
    }

    @PutMapping("/admin/articles")
    public Object updateArticle(Article article) {
        articleService.update(article);
        return new JsonResult(true);
    }

    @DeleteMapping("admin/articles/{id}")
    public Object deleteArticle(@PathVariable("id") int id) {
        articleService.delete(id);
        return new JsonResult(true);
    }
}
