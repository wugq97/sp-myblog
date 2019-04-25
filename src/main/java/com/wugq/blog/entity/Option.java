package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Option extends BaseBean{
    private int id = 0;
    private String title = "";
    private String img = "";
    private String content = "";
    private int userId = 0;
}
