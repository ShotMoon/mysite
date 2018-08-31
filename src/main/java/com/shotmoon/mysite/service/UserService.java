package com.shotmoon.mysite.service;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;

/**
 * @author shotmoon
 */
public interface UserService {

    ServerResponse<String> register(User user);

    ServerResponse<User> login(String username, String password);

    ServerResponse<User> updateUserInfo(User user);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);

    ServerResponse<String> getQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);
}
