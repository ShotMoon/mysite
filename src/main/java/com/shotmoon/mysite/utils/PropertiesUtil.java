package com.shotmoon.mysite.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author shotmoon
 */
@Component
public class PropertiesUtil {

    public static String passwordSalt;

    public static String redisIp;

    public static String redisPort;

    public static String redisMaxTotal;

    public static String redisMaxIdle;

    public static String redisMinIdle;

    public static String redisTestBorrow;

    public static String redisTestReturn;


    @Value("${password.salt}")
    public void setPasswordSalt(String passwordSalt) {
        PropertiesUtil.passwordSalt = passwordSalt;
    }

    @Value("${redis.ip}")
    public void setRedisIp(String redisIp) {
        PropertiesUtil.redisIp = redisIp;
    }

    @Value("${redis.port}")
    public void setRedisPort(String redisPort) {
        PropertiesUtil.redisPort = redisPort;
    }

    @Value("${redis.max.total}")
    public void setRedisMaxTotal(String redisMaxTotal) {
        PropertiesUtil.redisMaxTotal = redisMaxTotal;
    }

    @Value("${redis.max.idle}")
    public void setRedisMaxIdle(String redisMaxIdle) {
        PropertiesUtil.redisMaxIdle = redisMaxIdle;
    }

    @Value("${redis.min.idle}")
    public void setRedisMinIdle(String redisMinIdle) {
        PropertiesUtil.redisMinIdle = redisMinIdle;
    }

    @Value("${redis.test.borrow}")
    public void setRedisTestBorrow(String redisTestBorrow) {
        PropertiesUtil.redisTestBorrow = redisTestBorrow;
    }

    @Value("${redis.test.return}")
    public void setRedisTestReturn(String redisTestReturn) {
        PropertiesUtil.redisTestReturn = redisTestReturn;
    }
}
