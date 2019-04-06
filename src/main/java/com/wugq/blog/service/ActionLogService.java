package com.wugq.blog.service;

import com.wugq.blog.entity.ActionLog;

public interface ActionLogService {

    int insert(ActionLog actionLog);

    int update(ActionLog actionLog);

    ActionLog selectById(int id);

    int delete(int id);
}
