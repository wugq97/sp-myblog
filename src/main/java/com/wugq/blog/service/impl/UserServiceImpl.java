package com.wugq.blog.service.impl;

import com.wugq.blog.entity.User;
import com.wugq.blog.mapper.UserMapper;
import com.wugq.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return (User)request.getAttribute("user");
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }

    public int update(User user) {
        return userMapper.update(user);
    }

    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    public int delete(int id) {
        return userMapper.delete(id);
    }
}
