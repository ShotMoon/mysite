package com.shotmoon.mysite.service.impl;

import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.repository.UserRepository;
import com.shotmoon.mysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shotmoon
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(User user) {
        userRepository.save(user);
    }
}
