package com.wugq.blog.dto;

import com.wugq.blog.entity.file.FileInfo;
import lombok.Data;

@Data
public class FileInfoDto {
    private FileInfo file;
    private String username = "";
}
