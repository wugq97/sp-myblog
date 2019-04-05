package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Tag;
import com.wugq.blog.mapper.TagMapper;
import com.wugq.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    TagMapper tagMapper;

    public Tag selectById(int id) {
        return tagMapper.selectById(id);
    }
}
