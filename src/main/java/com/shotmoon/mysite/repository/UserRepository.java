package com.shotmoon.mysite.repository;

import com.shotmoon.mysite.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author shotmoon
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
