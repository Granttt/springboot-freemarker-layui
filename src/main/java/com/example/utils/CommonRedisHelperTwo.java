package com.example.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/11/1 19:34
 * @Description:springboot的RedisTemplate实现分布式锁
 * https://blog.csdn.net/long2010110/article/details/82911168
 * https://blog.csdn.net/qq_28397259/article/details/80839072
 * https://blog.csdn.net/weixin_33795806/article/details/92623519
 */
@Component
public class CommonRedisHelperTwo {
    private static final Long SUCCESS = 1L;

    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取锁
     * @param lockKey
     * @param value
     * @param expireTime：单位-秒
     * @return
     */
    public static boolean getLock(String lockKey, String value, int expireTime){
        boolean ret = false;
        try{
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";

            RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey),value,expireTime);

            if(SUCCESS.equals(result)){
                return true;
            }

        }catch(Exception e){

        }
        return ret;
    }

    /**
     * 释放锁
     * @param lockKey
     * @param value
     * @return
     */
    public static boolean releaseLock(String lockKey, String value){

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);

        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey),value);
        if(SUCCESS.equals(result)) {
            return true;
        }

        return false;
    }

    public  RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    @Resource(name = "redisTemplate")
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        CommonRedisHelperTwo.redisTemplate = redisTemplate;
    }
}