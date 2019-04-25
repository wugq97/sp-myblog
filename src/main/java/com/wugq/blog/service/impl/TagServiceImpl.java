package com.wugq.blog.service.impl;

import com.wugq.blog.entity.Tag;
import com.wugq.blog.mapper.TagMapper;
import com.wugq.blog.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class TagServiceImpl implements TagService {

    @Resource
    TagMapper tagMapper;

    public int insert(Tag tag) {
        return tagMapper.insert(tag);
    }

    public int update(Tag tag) {
        return tagMapper.update(tag);
    }

    public Tag selectById(int id) {
        return tagMapper.selectById(id);
    }

    public int delete(int id) {
        return tagMapper.delete(id);
    }

    public List<Tag> selectAll() { return tagMapper.selectAll(); }

    public List<Tag> selectByCondition(String name, Optional<PageInfo> pageInfo){
        Map<String,Object> param = new HashMap<>();
        param.put("name",name);
        if(pageInfo.isPresent()){
            param.put("start",pageInfo.get().getStart());
            param.put("count",pageInfo.get().getPerPage());
        }
        return tagMapper.selectByCondition(param);
    }
}
