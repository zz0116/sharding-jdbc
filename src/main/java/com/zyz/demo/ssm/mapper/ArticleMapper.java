package com.zyz.demo.ssm.mapper;

import com.zyz.demo.ssm.model.Article;
import com.zyz.demo.ssm.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {
    @Select("select title, content from article where uid = #{uid}")
    List<Article> getArticleByUser(@Param("uid") int uid);

    @Insert("insert into article (uid, title, content) values (#{article.uid}, #{article.title}, #{article.content})")
    boolean addArticle(@Param("Article") Article article);
}
