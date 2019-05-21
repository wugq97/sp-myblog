package com.wugq.blog.service.impl;

import com.wugq.blog.entity.file.FileInfo;
import com.wugq.blog.mapper.FileInfoMapper;
import com.wugq.blog.service.FileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    FileInfoMapper fileInfoMapper;

    @Override
    public int save(FileInfo fileInfo) {
        return fileInfoMapper.insert(fileInfo);
    }
}
