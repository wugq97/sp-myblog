package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Option;
import com.wugq.blog.mapper.OptionMapper;
import com.wugq.blog.service.OptionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OptionServiceImpl implements OptionService {

    @Resource
    OptionMapper optionMapper;

    public int insert(Option option) {
        return optionMapper.insert(option);
    }

    public int update(Option option) {
        return optionMapper.update(option);
    }

    public Option selectById(int id) {
        return optionMapper.selectById(id);
    }

    public int delete(int id) {
        return optionMapper.delete(id);
    }
}
