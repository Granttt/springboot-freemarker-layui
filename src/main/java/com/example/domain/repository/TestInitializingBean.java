package com.example.domain.repository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/27 14:37
 * @Description:
 */
@Configuration
public class TestInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ceshi InitializingBean");
    }
    public void testInit(){
        System.out.println("ceshi init-method");
    }
}