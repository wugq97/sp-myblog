package com.wugq.blog.mapper;

import org.apache.ibatis.annotations.Param;

public interface ViewMapper {

    void insert(@Param("articleId") int articleId, @Param("userId") int userId);

    int select(@Param("articleId")int articleId,@Param("userId") int userId);
}
