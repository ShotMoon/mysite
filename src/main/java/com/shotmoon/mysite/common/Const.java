package com.shotmoon.mysite.common;

/**
 * @author shotmoon
 */
public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String TOKEN_PREFIX = "token_";

    public interface RedisCacheExtime{
        int REDIS_SESSION_EXTIME = 60 * 30;//30分钟
    }
}
