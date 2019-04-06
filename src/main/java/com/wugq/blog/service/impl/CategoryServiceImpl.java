package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Category;
import com.wugq.blog.mapper.CategoryMapper;
import com.wugq.blog.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    public int update(Category category) {
        return categoryMapper.update(category);
    }

    public Category selectById(int id) {
        return categoryMapper.selectById(id);
    }

    public int delete(int id) {
        return categoryMapper.delete(id);
    }
}
