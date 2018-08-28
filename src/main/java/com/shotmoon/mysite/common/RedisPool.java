package com.shotmoon.mysite.common;

import com.shotmoon.mysite.utils.PropertiesUtil;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shotmoon
 */
public class RedisPool {

    private static JedisPool jedisPool;

    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.redisMaxTotal); //最大连接数
    private static Integer maxIdle = Integer.parseInt(PropertiesUtil.redisMaxIdle);//在jedispool中最大的idle状态(空闲的)的jedis实例的个数
    private static Integer minIdle = Integer.parseInt(PropertiesUtil.redisMinIdle);//在jedispool中最小的idle状态(空闲的)的jedis实例的个数

    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.redisTestBorrow);//在borrow一个jedis实例的时候，是否要进行验证操作，如果赋值true。则得到的jedis实例肯定是可以用的。
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.redisTestReturn);//在return一个jedis实例的时候，是否要进行验证操作，如果赋值true。则放回jedispool的jedis实例肯定是可以用的。

    private static String redisIp = PropertiesUtil.redisIp;
    private static Integer redisPort = Integer.parseInt(PropertiesUtil.redisPort);

    private static void initPool(){
        JedisPoolConfig config = new JedisPoolConfig();

        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);

        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);

        config.setBlockWhenExhausted(true);//连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。

        jedisPool = new JedisPool(config, redisIp, redisPort, 1000*2);
    }

    static {
        initPool();
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

    public static void returnBrokenResource(Jedis jedis){
        jedisPool.returnBrokenResource(jedis);
    }

    public static void returnResource(Jedis jedis){
        jedisPool.returnResource(jedis);
    }
}
