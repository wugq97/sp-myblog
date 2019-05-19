package com.wugq.blog.dto;

import lombok.Data;

@Data
public class ArticleFrontDto {
    private int id = 0;
    private String title = "";
    private String subtitle = "";
    private String category = "";
    private String img = "";
    private Integer views = 0;
    private String time = "";
}
