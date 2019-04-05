package com.wugq.blog.mapper;

import com.wugq.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TagMapper {

    Tag selectById(@Param("id") int id);

}
