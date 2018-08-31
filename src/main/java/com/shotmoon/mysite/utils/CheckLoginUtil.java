package com.shotmoon.mysite.utils;

import com.shotmoon.mysite.common.ServerResponse;
import com.shotmoon.mysite.domain.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shotmoon
 */
public class CheckLoginUtil {
    public static ServerResponse checkLogin(HttpServletRequest httpServletRequest) {
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        String userJsonStr = RedisPoolUtil.get(loginToken);
        User currentUser = JsonUtil.string2Obj(userJsonStr, User.class);
        if (currentUser == null) {
            return ServerResponse.createByErrorMessage("登录超时");
        }
        return ServerResponse.createBySuccess("校验成功,已登录", currentUser);
    }
}
