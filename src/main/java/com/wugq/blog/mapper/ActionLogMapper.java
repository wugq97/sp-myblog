package com.wugq.blog.mapper;

import com.wugq.blog.entity.ActionLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActionLogMapper {

    int insert(ActionLog actionLog);

    int update(ActionLog actionLog);

    ActionLog selectById(int id);

    int delete(int id);

    List<ActionLog> selectAll();
}
