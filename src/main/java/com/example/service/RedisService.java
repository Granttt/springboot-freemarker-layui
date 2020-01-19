package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/10/26 15:55
 * @Description:redis操作的工具类
 * springboot之使用redistemplate优雅地操作redis
 * https://www.cnblogs.com/superfj/p/9232482.html
 */
@Component
public class RedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;
    /**
     * 删除多个key
     *
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }
    public void deleteKeys() {
        deleteKey("s","dd","sdfsdf","ddde");
    }
}