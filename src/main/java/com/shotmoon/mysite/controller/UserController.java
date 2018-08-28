package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.common.Const;
import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.service.UserService;
import com.shotmoon.mysite.utils.CookieUtil;
import com.shotmoon.mysite.utils.JsonUtil;
import com.shotmoon.mysite.utils.RedisPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author shotmoon
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ServerResponse<String> register(@RequestBody User user){
        ServerResponse<String> response = userService.register(user);

        return response;
    }

    @GetMapping
    public ServerResponse<User> login(@RequestParam String username,
                                        @RequestParam String password,
                                        HttpServletResponse httpServletResponse,
                                        HttpSession session) {
        ServerResponse<User> response = userService.login(username, password);

        if (response.isSuccess()) {
            CookieUtil.writeLoginToken(httpServletResponse, session.getId());
            RedisPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);

        }
        return response;
    }

}
