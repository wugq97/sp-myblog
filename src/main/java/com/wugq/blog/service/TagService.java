package com.wugq.blog.service;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    int insert(Tag tag);

    int update(Tag tag);

    Tag selectById(int id);

    int delete(int id);

    List<Tag> selectAll();

    List<Tag> selectByCondition(String name, Optional<PageInfo> pageInfo);
}
