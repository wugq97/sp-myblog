package com.wugq.blog.entity;

import lombok.Data;

@Data
public class View extends BaseBean {
    private int id;
    private int articleId;
    private int userId;
}
