package com.shotmoon.mysite.repository;

import com.shotmoon.mysite.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shotmoon
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
