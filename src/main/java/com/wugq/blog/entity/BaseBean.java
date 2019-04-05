package com.wugq.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BaseBean {
    private Date created_at = null;
    private Date updated_at = null;
}
