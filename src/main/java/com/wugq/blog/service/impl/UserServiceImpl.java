package com.wugq.blog.service.impl;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.dto.UserDto;
import com.wugq.blog.entity.User;
import com.wugq.blog.mapper.ArticleMapper;
import com.wugq.blog.mapper.UserMapper;
import com.wugq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    ArticleMapper articleMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private Long expire = 60 * 60 * 24L; // seconds

    public String setToken(Integer id) {
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(token,id+"",expire,TimeUnit.SECONDS);
        return token;
    }

    public void deleteToken(HttpServletRequest request) {
        String token = request.getParameter("tk");
        try{
            stringRedisTemplate.delete(token);
        } catch (Exception e) {
            System.out.println("无此token");
        }
    }

    public User getByToken(String token) {
        String id = stringRedisTemplate.opsForValue().get(token);
        return selectById(Integer.parseInt(Optional.ofNullable(id).orElse("0")));
    }

    public User getLoginUser(HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        if(user==null){
            String token = request.getParameter("tk");
            user = getByToken(token);
        }
        return user;
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

    public User validate(String username, String password) {
        return userMapper.selectByName(username,password);
    }


    public void getUsers(Integer authority, PageInfo pageInfo) {
        pageInfo.setCount(userMapper.selectCountByAuthority(authority));
        List<User> users = userMapper.selectByAuthority(authority,pageInfo.getStartIndex(),pageInfo.getPerPage());
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(user -> {
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setAccount(user.getAccount());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setArticleNum(articleMapper.selectCountByUid(user.getId()));
            dto.setCreatedAt(user.getCreatedAt().getTime());
            dtos.add(dto);
        });
        pageInfo.setItems(dtos);
    }

    @Override
    public boolean getUserByName(String username) {
        int num = userMapper.selectByUserName(username);
        if(num > 0)
            return true;
        return false;
    }

    @Override
    public int updatePassword(int id, String oldPassword, String newPassword) {
        return userMapper.updatePassword(id,oldPassword,newPassword);
    }
}
