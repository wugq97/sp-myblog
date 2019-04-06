package com.wugq.blog.service;

import com.wugq.blog.entity.Notice;

public interface NoticeService {
    int insert(Notice notice);

    int update(Notice notice);

    Notice selectById(int id);

    int delete(int id);
}
