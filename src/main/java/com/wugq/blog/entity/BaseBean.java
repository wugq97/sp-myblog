package com.wugq.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseBean implements Serializable {

    private Date createdAt = null;

    private Date updatedAt = null;
}
