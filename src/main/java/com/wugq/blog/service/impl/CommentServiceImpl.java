package com.wugq.blog.service.impl;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.dto.CommentAdminDto;
import com.wugq.blog.dto.CommentDto;
import com.wugq.blog.entity.Comment;
import com.wugq.blog.mapper.CommentMapper;
import com.wugq.blog.service.ArticleService;
import com.wugq.blog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;
    @Resource
    ArticleService articleService;

    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    public int update(Comment comment) {
        return commentMapper.update(comment);
    }

    public Comment selectById(int id) {
        return commentMapper.selectById(id);
    }

    public int delete(int id) {
        return commentMapper.delete(id);
    }

    @Override
    public int getNums() {
        return commentMapper.selectNums();
    }

    @Override
    public List<Integer> getHotArticleIds() {
        return commentMapper.selectHotArticleIds();
    }

    @Override
    public List<CommentDto> getCommentsByArticle(int articleId) {
        List<Comment> parent = commentMapper.selectByArticle(articleId,1);
        List<Comment> child = commentMapper.selectByArticle(articleId,2);
        List<CommentDto> dtos = new ArrayList<>();
        parent.forEach(comment -> {
            CommentDto dto = new CommentDto();
            dto.setParent(comment);
            int id = comment.getId();
            List<Comment> cs = child.stream().filter(c -> c.getPid() == id).collect(Collectors.toList());
            dto.setChild(cs);
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public void getComments(PageInfo pageInfo) {
        List<Comment> comments = commentMapper.select(pageInfo.getStartIndex(),pageInfo.getPerPage());
        pageInfo.setCount(commentMapper.selectNums());
        List<CommentAdminDto> dtos = new ArrayList<>();
        comments.forEach(comment -> {
            CommentAdminDto dto = new CommentAdminDto();
            dto.setId(comment.getId());
            dto.setArticle(articleService.selectById(comment.getArticleId()).getTitle());
            dto.setContent(comment.getContent());
            dto.setCreatedAt(comment.getCreatedAt());
            dto.setReplyName(comment.getReplyName());
            dto.setUsername(comment.getUsername());
            dtos.add(dto);
        });
        pageInfo.setItems(dtos);
    }

    @Override
    public void deleteByArticle(int articleId) {
        commentMapper.deleteByArticle(articleId);
    }
}
