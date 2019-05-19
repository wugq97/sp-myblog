package com.wugq.blog.mapper;

import com.wugq.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {

    int insert(Tag tag);

    int update(Tag tag);

    Tag selectById(int id);

    int delete(int id);

    List<Tag> selectAll();

    List<Tag> selectList(@Param("start") Integer start,@Param("count") Integer count);

    Integer selectCount();

}
