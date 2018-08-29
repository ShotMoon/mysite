package com.shotmoon.mysite.repository;

import com.shotmoon.mysite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author shotmoon
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("select count(*) from User u where u.email=?1 and u.id!=?2")
    int findByEmailAndId(String email, int id);
}
