package com.wugq.blog.service;

import com.wugq.blog.entity.User;

public interface UserService {
    int insert(User user);

    int update(User user);

    User selectById(int id);

    int delete(int id);
}
