package com.wugq.blog.service;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Category;

import java.util.List;

public interface CategoryService {
    int insert(Category category);

    Category update(Category category);

    Category selectById(int id);

    int delete(int id);

    void selectByPage(PageInfo pageInfo);

    List<Category> getParentCategories();

    List<Category> getChildCategories();

    List<Category> getAll();

    int getNums();
}
