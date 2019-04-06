package com.wugq.blog.mapper;

import com.wugq.blog.entity.ActionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActionLogMapper {

    int insert(ActionLog actionLog);

    int update(ActionLog actionLog);

    ActionLog selectById(int id);

    int delete(int id);
}
