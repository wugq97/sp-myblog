package com.wugq.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentAdminDto {
    private int id;
    private String username;
    private String replyName;
    private String content;
    private String article;
    private Date createdAt;
}
