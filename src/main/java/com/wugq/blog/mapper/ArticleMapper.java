package com.wugq.blog.mapper;

import com.wugq.blog.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ArticleMapper {
    int insert(Article article);

    int update(Article article);

    Article selectById(int id);

    int delete(int id);

    int selectCountByUid(@Param("uid") int uid);

    int selectCountByTag(@Param("tagId") int tagId);

    List<Article>  selectByCondition(@Param("uid") int uid,@Param("parentCategoryId") int parentCategoryId,
               @Param("childCategoryId") int childCategoryId, @Param("start") int start,@Param("count") int count);

    int selectCountByCondition(@Param("uid") int uid,@Param("parentCategoryId") int parentCategoryId,
                               @Param("childCategoryId") int childCategoryId);

    int selectNums();

    Date selectLastUpdated();

    List<Article> selectHot(@Param("ids") List<Integer> articleIds);

    List<Article> selectFront(@Param("category") int category, @Param("tag") int tag,
                              @Param("start") int start, @Param("count") int count);

    int selectCountFront(@Param("category") int category, @Param("tag") int tag);

    List<Article> selectSearch(@Param("text") String text);

    List<Article> selectRecommend();

    int selectByCategory(@Param("categoryId") int categoryId);
}
