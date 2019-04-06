package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Category {
    private int id = 0;
    private int pid = 0;
    private String name = "";
    private String decription = "";
}
