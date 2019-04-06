package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Comment {
    private int  id = 0;
    private int articleId = 0;
    private int pid = 0;
    private String username = "";
    private String content = "";
}