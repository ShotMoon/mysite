package com.shotmoon.mysite.service.impl;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.repository.UserRepository;
import com.shotmoon.mysite.service.UserService;
import com.shotmoon.mysite.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
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
    public ServerResponse<String> register(User user) {
        ServerResponse<String> validResponse = this.checkValid(user);
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        //MD5
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        User saveResult = userRepository.save(user);
        if (saveResult != null) {
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("注册失败");
    }

    @Override
    public ServerResponse<User> login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        String encodePassword = MD5Util.MD5EncodeUtf8(password);
        if (!StringUtils.equals(user.getPassword(), encodePassword)) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        return ServerResponse.createBySuccess("登录成功", user);
    }

    //校验用户名，邮箱是否已存在
    private ServerResponse<String> checkValid(User user){
        String username = user.getUsername();
        String email = user.getEmail();

        if (userRepository.findByUsername(username) != null) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }

        if (userRepository.findByEmail(email) != null) {
            return ServerResponse.createByErrorMessage("邮箱已存在");
        }

        return ServerResponse.createBySuccessMessage("校验成功");

    }
}
