package com.wugq.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id = 0;
    private String username = "";
    private String email = "";
    private String account = "";
    private String password = "";
    private String lastLoginIp = "";
    private Date lastLoginTime = null;
    private int authority = 0;
}
