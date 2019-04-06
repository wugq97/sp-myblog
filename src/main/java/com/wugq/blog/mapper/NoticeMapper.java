package com.wugq.blog.mapper;

import com.wugq.blog.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    int insert(Notice notice);

    int update(Notice notice);

    Notice selectById(int id);

    int delete(int id);
}
