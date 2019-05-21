package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.entity.ActionLog;
import com.wugq.blog.service.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ActionLogController {

    @Autowired
    ActionLogService actionLogService;

    @GetMapping("actionLogs")
    public Object getLogs() {
        List<ActionLog> logs = actionLogService.getLogs();
        return new JsonResult(logs);
    }
}
