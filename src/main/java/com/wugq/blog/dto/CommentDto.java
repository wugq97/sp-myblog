package com.wugq.blog.dto;

import com.wugq.blog.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentDto {
    private Comment parent;
    private List<Comment> child;
}
