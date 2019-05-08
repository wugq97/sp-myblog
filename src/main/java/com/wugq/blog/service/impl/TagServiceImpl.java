package com.wugq.blog.service.impl;

import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.Tag;
import com.wugq.blog.mapper.ArticleMapper;
import com.wugq.blog.mapper.TagMapper;
import com.wugq.blog.service.TagService;
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

    public int insert(Tag tag) {
        return tagMapper.insert(tag);
    }

    public int update(Tag tag) {
        return tagMapper.update(tag);
    }

    public Tag selectById(int id) {
        Tag tag = tagMapper.selectById(id);
        System.out.println("9999");
        return tag;
    }

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
}
