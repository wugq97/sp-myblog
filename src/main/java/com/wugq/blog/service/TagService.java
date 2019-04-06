package com.wugq.blog.service;

import com.wugq.blog.entity.Tag;

public interface TagService {
    int insert(Tag tag);

    int update(Tag tag);

    Tag selectById(int id);

    int delete(int id);
}
