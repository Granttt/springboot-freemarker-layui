package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @auther: 高希阳
 * @Date: 2019/1/24 16:55
 * @Description:
 * -3
 */
@Service("iCacheService")
public class CacheSingleService implements ICacheService{

    @Resource
    private RedisTemplate<String, ?> redisTemplate;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public boolean set(final String key, final String value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        return result;
    }

    @Override
    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value = connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public boolean remove(final String key) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.del(key.getBytes());
                return true;
            }
        });
        return result;
    }
    @Override
    public Long incrBy(String key, long increament) {
        Long i = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            i = jedis.incrBy(key, increament);
        }finally {
            if (jedisPool != null) {
                jedisPool.close();
            }
        }

        return i;
    }

    @Override
    public boolean existKey(String key) {
        Jedis client = jedisPool.getResource();
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        try {
            byte[] keyBytes = serializer.serialize(key);
            return client.exists(keyBytes);
        } finally {
            return false;
        }
    }

}
