package com.wugq.blog.service.impl;

import com.wugq.blog.entity.ActionLog;
import com.wugq.blog.mapper.ActionLogMapper;
import com.wugq.blog.service.ActionLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActionLogServiceImpl implements ActionLogService {

    @Resource
    ActionLogMapper actionLogMapper;

    public int insert(ActionLog actionLog) {
        return actionLogMapper.insert(actionLog);
    }

    public int update(ActionLog actionLog) {
        return actionLogMapper.update(actionLog);
    }

    public ActionLog selectById(int id) {
        return actionLogMapper.selectById(id);
    }

    public int delete(int id) {
        return actionLogMapper.delete(id);
    }

    @Override
    public List<ActionLog> getLogs() {
        return actionLogMapper.selectAll();
    }
}
