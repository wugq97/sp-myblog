package com.wugq.blog.service;

import com.wugq.blog.entity.Option;

public interface OptionService {
    int insert(Option option);

    int update(Option option);

    Option selectById(int id);

    int delete(int id);
}
