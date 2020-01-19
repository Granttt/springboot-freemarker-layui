package com.example.domain.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachingConfigurerSupport;

/**
 * @auther: 高希阳
 * @Date: 2019/1/24 11:01
 * @Description:
 */
public class JedisConfig extends CachingConfigurerSupport {
    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);
}
