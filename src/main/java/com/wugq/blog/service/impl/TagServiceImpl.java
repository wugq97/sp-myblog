package com.wugq.blog.service.impl;

import com.wugq.blog.annotation.MyLog;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Tag;
import com.wugq.blog.mapper.ArticleMapper;
import com.wugq.blog.mapper.TagMapper;
import com.wugq.blog.service.TagService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TagServiceImpl implements TagService {

    @Resource
    TagMapper tagMapper;
    @Resource
    ArticleMapper articleMapper;

    @MyLog(value = "新增标签", kind = "tag", operator = "add")
    public int insert(Tag tag) {
        return tagMapper.insert(tag);
    }

    @MyLog(value = "更新标签", kind = "tag", operator = "update")
    @CachePut(value="tag",key="#tag.id")
    public Tag update(Tag tag) {
         tagMapper.update(tag);
         return tag;
    }

    @Cacheable(value = "tag",key = "#id",condition = "#id>0")
    public Tag selectById(int id) {
        Tag tag = tagMapper.selectById(id);
        return tag;
    }

    @MyLog(value = "删除标签", kind = "tag", operator = "delete")
    @CacheEvict(value = "tag", key = "#id")
    public int delete(int id) {
        return tagMapper.delete(id);
    }

    public List<Tag> selectAll() { return tagMapper.selectAll(); }

    public void getTags(PageInfo pageInfo){
        pageInfo.setCount(tagMapper.selectCount());
        List<Tag> tags = tagMapper.selectList(pageInfo.getStartIndex(),pageInfo.getPerPage());
        List<Map<String,Object>> result = new ArrayList();
        tags.forEach(tag -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id",tag.getId());
            map.put("name",tag.getName());
            map.put("description",tag.getDescription());
            int articleNum = articleMapper.selectCountByTag(tag.getId());
            map.put("articleNum",articleNum);
            result.add(map);
        });
        pageInfo.setItems(result);
    }

    @Override
    public int getNums() {
        return tagMapper.selectCount();
    }

    @Override
    public List<Tag> getAll() {
        return tagMapper.selectAll();
    }
}
