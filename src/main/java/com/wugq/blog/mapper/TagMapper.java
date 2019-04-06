package com.wugq.blog.mapper;

import com.wugq.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TagMapper {

    int insert(Tag tag);

    int update(Tag tag);

    Tag selectById(int id);

    int delete(int id);

}
