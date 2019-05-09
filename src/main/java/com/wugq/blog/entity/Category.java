package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Category extends BaseBean {
    private int id = 0;
    private int pid = 0;
    private String name = "";
    private String description = "";
}
