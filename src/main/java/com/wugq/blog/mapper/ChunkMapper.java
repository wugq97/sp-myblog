package com.wugq.blog.mapper;

import com.wugq.blog.entity.file.Chunk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChunkMapper {

    int insert(Chunk chunk);

    int selectByIdentifierAndChunkNumber(@Param("identifier") String identifier,@Param("chunkNumber") int chunkNumber);
}
