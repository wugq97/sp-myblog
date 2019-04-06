package com.wugq.blog.mapper;

import com.wugq.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    int insert(Category category);

    int update(Category category);

    Category selectById(int id);

    int delete(int id);
}
