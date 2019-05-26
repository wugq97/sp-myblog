package com.wugq.blog.service;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.file.FileInfo;

import java.util.List;

public interface FileInfoService {

    int save(FileInfo fileInfo);

    FileInfo get(Integer id);

    int update(FileInfo fileInfo);

    int delete(int id);

    List<FileInfo> getPublicFiles();

    List<FileInfo> getPrivateFiles(int uid);

    void getAdminFile(int flag, PageInfo pageInfo);
}
