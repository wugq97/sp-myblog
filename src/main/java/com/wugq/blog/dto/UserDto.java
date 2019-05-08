package com.wugq.blog.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String username;
    private String account;
    private String email;
    private Integer articleNum;
    private Long createdAt;
}
