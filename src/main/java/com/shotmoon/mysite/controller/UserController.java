package com.shotmoon.mysite.controller;

import com.shotmoon.mysite.common.Const;
import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;
import com.shotmoon.mysite.service.UserService;
import com.shotmoon.mysite.utils.CheckLoginUtil;
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
    public ServerResponse<String> register(@RequestBody User user) {
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

    @DeleteMapping
    public ServerResponse<String> logout(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        CookieUtil.delLoginToken(httpServletRequest,httpServletResponse);
        RedisPoolUtil.del(loginToken);

        return ServerResponse.createBySuccessMessage("登出成功");
    }

    @GetMapping
    public ServerResponse<User> getUserInfo(HttpServletRequest httpServletRequest) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }
        User currentUser = response.getData();

        return ServerResponse.createBySuccess(currentUser);
    }

    @PutMapping
    public ServerResponse<User> updateUserInfo(HttpServletRequest httpServletRequest, @RequestBody User user) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User currentUser = JsonUtil.string2Obj(userJsonStr, User.class);

        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("登录超时");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());

        //TODO password
        ServerResponse<User> response = userService.updateUserInfo(user);
        if (response.isSuccess()) {
            response.getData().setUsername(currentUser.getUsername());
            RedisPoolUtil.setEx(loginToken, JsonUtil.obj2String(response.getData()), Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return response;
    }

    @PutMapping("/password")
    public ServerResponse resetPassword(HttpServletRequest httpServletRequest,
                                                @RequestParam String passwordOld,
                                                @RequestParam String passwordNew) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }

        return userService.resetPassword(passwordOld, passwordNew, response.getData());
    }

    @GetMapping("/forget/question/{username}")
    public ServerResponse forgetGetQuestion(HttpServletRequest httpServletRequest,
                                                    @PathVariable String username){
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }
        return userService.getQuestion(username);
    }

    @PostMapping("/forget/password")
    public ServerResponse forgetCheckAnswer(HttpServletRequest httpServletRequest,
                                                    String username,
                                                    String question,
                                                    String answer) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }
        return userService.checkAnswer(username, question, answer);
    }

    @PutMapping("/forget/password")
    public ServerResponse forgetRestPassword(HttpServletRequest httpServletRequest,
                                                     String username,
                                                     String passwordNew,
                                                     String forgetToken) {
        ServerResponse<User> response = CheckLoginUtil.checkLogin(httpServletRequest);
        if (!response.isSuccess()) {
            return response;
        }
        return userService.forgetResetPassword(username, passwordNew, forgetToken);
    }

    @GetMapping("/cookie")
    public String getCookie(HttpServletRequest httpServletRequest) {
        return CookieUtil.readLoginToken(httpServletRequest);

    }
}
