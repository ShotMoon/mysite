package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shotmoon
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void register(User user){
        userService.register(user);
    }

}
