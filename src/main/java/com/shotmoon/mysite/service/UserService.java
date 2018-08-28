package com.shotmoon.mysite.service;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;

/**
 * @author shotmoon
 */
public interface UserService {

    ServerResponse<String> register(User user);

    ServerResponse<User> login(String username, String password);
}
