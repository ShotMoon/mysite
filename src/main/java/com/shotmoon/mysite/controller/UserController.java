package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.common.Const;
import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.service.UserService;
import com.shotmoon.mysite.utils.CookieUtil;
import com.shotmoon.mysite.utils.JsonUtil;
import com.shotmoon.mysite.utils.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/{username}")
    public ServerResponse<User> login(@PathVariable String username,
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

    @GetMapping
    public ServerResponse<User> getUserInfo(HttpServletRequest httpServletRequest) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr,User.class);

        if(user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }

    @PutMapping
    public ServerResponse<User> updateUserInfo(HttpServletRequest httpServletRequest,@RequestBody User user) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if(StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User currentUser = JsonUtil.string2Obj(userJsonStr,User.class);

        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());

        ServerResponse<User> response = userService.updateUserInfo(user);
        if(response.isSuccess()){
            response.getData().setUsername(currentUser.getUsername());
            RedisPoolUtil.setEx(loginToken, JsonUtil.obj2String(response.getData()),Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return response;
    }

}
