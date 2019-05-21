package com.wugq.blog.entity.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class Chunk implements Serializable {
    private int id;

    private int chunkNumber; // 当前文件块

    private Long chunkSize; // 分块大小

    private Long currentChunkSize; // 当前分块大小

    private Long totalSize; // 总大小

    private String identifier; // 文件标识

    private String filename; // 文件名

    private String relativePath; // 相对路径

    private Integer totalChunks; // 总块数

    private String type; // 文件类型

    private MultipartFile file;

}
