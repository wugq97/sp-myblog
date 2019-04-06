package com.wugq.blog.entity;

import lombok.Data;

@Data
public class Article extends BaseBean{
    private int id = 0;
    private int userId = 0;
    private String title = "";
    private String subTitle = "";
    private String content = "";
    private int categoryIdParent = 0;
    private int getCategoryIdChild = 0;
    private String tagIds = "";
    private String img = "";
    private int views = 0;
    private int likes = 0;
}
