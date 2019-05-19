package com.wugq.blog.mapper;

import com.wugq.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User user);

    int update(User user);

    User selectById(int id);

    int delete(int id);

    User selectByName(@Param("username") String username, @Param("password") String password);

    List<User> selectByAuthority(@Param("authority")int authority,@Param("start")int start,@Param("count")int count);

    Integer selectCountByAuthority(@Param("authority")int authority);

    int selectByUserName(@Param("username") String username);

    int updatePassword(@Param("id")int id, @Param("oldPassword") String oldPassword,@Param("newPassword") String newPassword);
}
