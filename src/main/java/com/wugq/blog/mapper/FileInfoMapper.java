package com.wugq.blog.mapper;

import com.wugq.blog.entity.file.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileInfoMapper {

    int insert(FileInfo fileInfo);

    FileInfo select(int id);

    int update(FileInfo fileInfo);

    int delete(int id);

    List<FileInfo> getFiles(@Param("uid") Integer uid,@Param("flag") Integer flag,@Param("status") Integer status);

    List<FileInfo> selectAdminFiles(@Param("flag") int flag,@Param("start") int start,@Param("count") int count);

    int selectCountAdminFiles(@Param("flag") int flag);
}
