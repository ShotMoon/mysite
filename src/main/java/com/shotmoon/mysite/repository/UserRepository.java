package com.shotmoon.mysite.repository;

import com.shotmoon.mysite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author shotmoon
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}
