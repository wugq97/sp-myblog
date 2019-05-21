package com.wugq.blog.service;

import com.wugq.blog.entity.file.Chunk;

public interface ChunkService {

    void saveChunk(Chunk chunk);

    boolean checkChunk(String identifier, Integer chunkNumber);
}
