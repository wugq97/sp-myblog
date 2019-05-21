package com.wugq.blog.service.impl;

import com.wugq.blog.entity.file.Chunk;
import com.wugq.blog.mapper.ChunkMapper;
import com.wugq.blog.service.ChunkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ChunkServiceImpl implements ChunkService {

    @Resource
    ChunkMapper chunkMapper;

    public void saveChunk(Chunk chunk) {
        chunkMapper.insert(chunk);
    }

    public boolean checkChunk(String identifier, Integer chunkNumber) {
        int num = chunkMapper.selectByIdentifierAndChunkNumber(identifier,chunkNumber);
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }
}
