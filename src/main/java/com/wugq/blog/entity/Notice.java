package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Notice {
    private int id = 0;
    private String title = "";
    private String content = "";
    private String url = "";
    private int order = 0;
}
