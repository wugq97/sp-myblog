package com.wugq.blog.mapper;

import com.wugq.blog.entity.file.FileInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileInfoMapper {

    int insert(FileInfo fileInfo);
}
