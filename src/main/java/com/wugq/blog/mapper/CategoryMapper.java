package com.wugq.blog.mapper;

import com.wugq.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int insert(Category category);

    int update(Category category);

    Category selectById(int id);

    int delete(int id);

    List<Category> selectByPage(@Param("start") int page,@Param("count") int pageSize);

    int selectCount(@Param("isParent") boolean isParent);

    List<Category> getParentCategories();

    List<Category> getChildCategories();

    List<Category> selectAll();

    int selectNums();
}
