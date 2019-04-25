package com.wugq.blog.service;

import com.wugq.blog.entity.Category;

import java.util.List;

public interface CategoryService {
    int insert(Category category);

    int update(Category category);

    Category selectById(int id);

    int delete(int id);

    List<Category> selectAll();
}
