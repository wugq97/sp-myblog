package com.wugq.blog.entity.file;

import com.wugq.blog.entity.BaseBean;
import lombok.Data;

import java.io.Serializable;

@Data
public class FileInfo extends BaseBean implements Serializable {
    private int id;
    private int uid;
    private int flag;// 公共0 私人1
    private int status = 0;// 审核状态 0未通过 1已通过
    private String filename;
    private String identifier;
    private Long totalSize;
    private String type;
    private String location;
}
