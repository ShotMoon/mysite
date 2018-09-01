package com.shotmoon.mysite.repository;

import com.shotmoon.mysite.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shotmoon
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Modifying
    @Transactional
    @Query("update Article a set a.title=?1,a.content=?2,a.categoryId=?3 where a.id=?4")
    int updateArticleContent(String title, String content, int categoryId, int articleId);
}
