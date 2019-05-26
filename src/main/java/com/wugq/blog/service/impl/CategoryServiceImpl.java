package com.wugq.blog.service.impl;

import com.wugq.blog.annotation.MyLog;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Category;
import com.wugq.blog.mapper.CategoryMapper;
import com.wugq.blog.service.CategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @MyLog(value = "新增类别", kind = "category", operator = "add")
    public int insert(Category category) {
        return categoryMapper.insert(category);
    }

    @MyLog(value = "更新类别", kind = "category", operator = "update")
    @CachePut(value="category",key="#category.id")
    public Category update(Category category) {
         categoryMapper.update(category);
         return category;
    }

    @Cacheable(value = "category",key = "#id",condition = "#id>0")
    public Category selectById(int id) {
        return categoryMapper.selectById(id);
    }

    @MyLog(value = "删除类别", kind = "category", operator = "delete")
    @CacheEvict(value = "category", key = "#id")
    public int delete(int id) {
        return categoryMapper.delete(id);
    }

    public void selectByPage(PageInfo pageInfo) {
        List<Category> categories = categoryMapper.selectByPage(pageInfo.getStartIndex(),pageInfo.getPerPage());
        pageInfo.setCount(categoryMapper.selectCount(false));
        pageInfo.setItems(categories);
    }

    public List<Category> getParentCategories() {
        return categoryMapper.getParentCategories();
    }

    public List<Category> getChildCategories() {
        return categoryMapper.getChildCategories();
    }

    public List<Category> getAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public int getNums() {
        return categoryMapper.selectNums();
    }
}
