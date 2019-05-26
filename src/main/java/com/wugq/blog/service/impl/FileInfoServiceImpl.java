package com.wugq.blog.service.impl;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.dto.FileInfoDto;
import com.wugq.blog.entity.User;
import com.wugq.blog.entity.file.FileInfo;
import com.wugq.blog.mapper.FileInfoMapper;
import com.wugq.blog.service.FileInfoService;
import com.wugq.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    FileInfoMapper fileInfoMapper;
    @Resource
    UserService userService;

    @Override
    public int save(FileInfo fileInfo) {
        return fileInfoMapper.insert(fileInfo);
    }

    @Override
    public FileInfo get(Integer id) {
        return fileInfoMapper.select(id);
    }

    @Override
    public int update(FileInfo fileInfo) {
        return fileInfoMapper.update(fileInfo);
    }

    @Override
    public int delete(int id) {
        return fileInfoMapper.delete(id);
    }

    @Override
    public List<FileInfo> getPublicFiles() {
        return fileInfoMapper.getFiles(null,0,1);
    }

    @Override
    public List<FileInfo> getPrivateFiles(int uid) {
        return fileInfoMapper.getFiles(uid,1,null);
    }

    @Override
    public void getAdminFile(int flag, PageInfo pageInfo) {
        List<FileInfo> files = fileInfoMapper.selectAdminFiles(flag,pageInfo.getStartIndex(),pageInfo.getPerPage());
        List<FileInfoDto> dtos = new ArrayList<>();
        files.forEach(fileInfo -> {
            FileInfoDto dto = new FileInfoDto();
            dto.setFile(fileInfo);
            User user = userService.selectById(fileInfo.getUid());
            if (user != null) {
                dto.setUsername(user.getUsername());
            }
            dtos.add(dto);
        });
        pageInfo.setItems(dtos);
        pageInfo.setCount(fileInfoMapper.selectCountAdminFiles(flag));
    }
}
