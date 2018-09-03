package com.shotmoon.mysite.repository;

import com.shotmoon.mysite.domain.Praise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shotmoon
 */
public interface PraiseRepository extends JpaRepository<Praise, Integer> {

    Praise findByUserIdAndArticleId(int userId, int articleId);

    @Modifying
    @Transactional
    @Query("delete Praise p where p.userId=?1 and p.articleId=?2")
    void deleteByUserIdAndArticleId(int userId, int articleId);
}
