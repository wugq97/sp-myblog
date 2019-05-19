package com.wugq.blog.service.impl;

import com.wugq.blog.mapper.ViewMapper;
import com.wugq.blog.service.ViewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ViewServiceImpl implements ViewService {

    @Resource
    ViewMapper viewMapper;

    @Override
    public int getView(int articleId, int userId) {
        return viewMapper.select(articleId,userId);
    }

    @Override
    public void addView(int articleId, int userId) {
        viewMapper.insert(articleId,userId);
    }
}
