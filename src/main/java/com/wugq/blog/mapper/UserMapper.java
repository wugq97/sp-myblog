package com.wugq.blog.mapper;

import com.wugq.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User user);

    int update(User user);

    User selectById(int id);

    int delete(int id);

}
