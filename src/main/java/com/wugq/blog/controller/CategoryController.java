package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Category;
import com.wugq.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

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
        categoryService.delete(id);
        return new JsonResult(true);
    }
}
