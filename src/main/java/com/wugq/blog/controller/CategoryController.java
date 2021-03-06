package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Category;
import com.wugq.blog.service.ArticleService;
import com.wugq.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ArticleService articleService;

    @GetMapping("/admin/categories")
    public Object getCategories(Integer page,Integer pageSize) {
        PageInfo pageInfo = new PageInfo(page,pageSize);
        categoryService.selectByPage(pageInfo);
        return new JsonResult(pageInfo);
    }

    @GetMapping("/admin/categories/{id}")
    public Object getCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.selectById(id);
        return new JsonResult(category);
    }

    @GetMapping("/admin/categories/parent")
    public Object getParentCategories() {
        List<Category> categories = categoryService.getParentCategories();
        return new JsonResult(categories);
    }

    @PostMapping("/admin/categories")
    public Object addCategory(Category category) {
        categoryService.insert(category);
        return new JsonResult(true);
    }

    @PutMapping("/admin/categories")
    public Object updateCategories(Category category) {
        categoryService.update(category);
        return new JsonResult(true);
    }

    @DeleteMapping("/admin/categories/{id}")
    public Object deleteCategory(@PathVariable("id") Integer id) {
        int num = articleService.selectByCategory(id);
        if(num > 0) {
            return new JsonResult(false);
        }
        categoryService.delete(id);
        return new JsonResult(true);
    }

    /* 前台 */
    @GetMapping("/front/categories")
    public Object getAllCategories() {
        Map<String,Object> result = new HashMap<>();
        List<Category> parentCategories = categoryService.getParentCategories();
        List<Category> childCategories = categoryService.getChildCategories();
        result.put("parents",parentCategories);
        result.put("child",childCategories);
        return new JsonResult(result);
    }
}
