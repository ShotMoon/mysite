package com.shotmoon.mysite.repository;

import com.shotmoon.mysite.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


/**
 * @author shotmoon
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUserTest(){
        User user1 = new User();
        user1.setUsername("testName1");
        user1.setPassword("123123");
        User user = userRepository.save(user1);
        System.out.println(user.getUsername());
    }
}