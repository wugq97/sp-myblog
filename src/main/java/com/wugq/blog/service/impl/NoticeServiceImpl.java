package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Notice;
import com.wugq.blog.mapper.NoticeMapper;
import com.wugq.blog.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    public int insert(Notice notice) {
        return noticeMapper.insert(notice);
    }

    public int update(Notice notice) {
        return noticeMapper.update(notice);
    }

    public Notice selectById(int id) {
        return noticeMapper.selectById(id);
    }

    public int delete(int id) {
        return noticeMapper.delete(id);
    }
}
