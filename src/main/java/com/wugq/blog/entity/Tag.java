package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Tag extends BaseBean {
    private int id = 0;
    private String name = "";
    private String description = "";
    private int status = 0;
}
