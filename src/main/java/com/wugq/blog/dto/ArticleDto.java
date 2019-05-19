package com.wugq.blog.dto;

import com.wugq.blog.entity.BaseBean;
import lombok.Data;

@Data
public class ArticleDto extends BaseBean {
    private int id = 0;
    private int userId = 0;
    private String username = "";
    private String title = "";
    private String subTitle = "";
    private String content = "";
    private int categoryIdParent = 0;
    private String categoryParentName = "";
    private int getCategoryIdChild = 0;
    private String categoryChildName = "";
    private String tagIds = "";
    private String tags = "";
    private String img = "";
    private int views = 0;
    private int likes = 0;
    private int status = 0;
}
