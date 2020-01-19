package com.example.domain.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Description: 从零开始的Spring Session(二)
 * @Author: gaoxi
 * @CreateDate: 2019/6/15 22:21
 * @Version: 1.0
 * http://blog.didispace.com/spring-session-xjf-2/
 */
@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
}