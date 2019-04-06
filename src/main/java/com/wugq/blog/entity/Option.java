package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Option {
    private int id = 0;
    private String title = "";
    private String img = "";
    private String content = "";
    private int userId = 0;
}
