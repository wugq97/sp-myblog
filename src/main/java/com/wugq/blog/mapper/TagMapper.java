package com.wugq.blog.mapper;

import com.wugq.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagMapper {

    int insert(Tag tag);

    int update(Tag tag);

    Tag selectById(int id);

    int delete(int id);

    List<Tag> selectAll();

    List<Tag> selectByCondition(Map param);
}
