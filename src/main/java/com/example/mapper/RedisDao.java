package com.example.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @auther: 高希阳
 * @Date: 2018/12/26 12:27
 * @Description:
 * https://blog.csdn.net/forezp/article/details/70991675
 * SpringBoot非官方教程 | 第九篇： springboot整合Redis(1)
 * 数据访问层dao
 * 通过redisTemplate来访问redis.
 */
@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate template;
    /**
     * 功能描述：StringRedisTemplate操作redis数据
     * @author gxy
     * @date 2018/12/26 12:32
     * @param  http://www.cnblogs.com/slowcity/p/9002660.html
     * @return
     */
    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
//        ops.set(key,value,2, TimeUnit.MINUTES);//设置过期时间2分钟
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

}
