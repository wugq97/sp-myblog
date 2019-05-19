package com.wugq.blog.service;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    String setToken(Integer id);

    void deleteToken(HttpServletRequest request);

    User getByToken(String token);

    User getLoginUser(HttpServletRequest request);

    int insert(User user);

    int update(User user);

    User selectById(int id);

    int delete(int id);

    User validate(String username,String password);

    void getUsers(Integer authority, PageInfo pageInfo);

    boolean getUserByName(String username);

    int updatePassword(int id,String oldPassword,String newPassword);
}
