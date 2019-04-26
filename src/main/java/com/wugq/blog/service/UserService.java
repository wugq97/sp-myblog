package com.wugq.blog.service;

import com.wugq.blog.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    User getLoginUser(HttpServletRequest request);

    int insert(User user);

    int update(User user);

    User selectById(int id);

    int delete(int id);
}
