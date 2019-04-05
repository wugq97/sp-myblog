package com.wugq.blog;

import com.wugq.blog.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    TagService tagService;

    @Test
    public void contextLoads() {
        System.out.println(tagService.selectById(1));
    }

}
