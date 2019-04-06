package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Tag;
import com.wugq.blog.mapper.TagMapper;
import com.wugq.blog.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    TagMapper tagMapper;

    public int insert(Tag tag) {
        return tagMapper.insert(tag);
    }

    public int update(Tag tag) {
        return tagMapper.update(tag);
    }

    public Tag selectById(int id) {
        return tagMapper.selectById(id);
    }

    public int delete(int id) {
        return tagMapper.delete(id);
    }
}
