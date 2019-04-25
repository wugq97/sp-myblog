package com.wugq.blog.entity;

import lombok.Data;

@Data
public class ActionLog extends BaseBean{
    private int id = 0;
    private String action = "";
    private String object = "";
    private int userId = 0;
    private String ip = "";
}
