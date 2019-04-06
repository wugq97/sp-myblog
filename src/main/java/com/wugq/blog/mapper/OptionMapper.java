package com.wugq.blog.mapper;

import com.wugq.blog.entity.Option;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionMapper {
    int insert(Option option);

    int update(Option option);

    Option selectById(int id);

    int delete(int id);
}
