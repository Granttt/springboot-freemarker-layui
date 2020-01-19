package com.example.domain.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Description:自定义事务
 * @Author: gaoxi
 * @CreateDate: 2019/10/13 11:26
 * @Version: 1.0
 * https://blog.csdn.net/kysmkj/article/details/82876307
 * 只用到了教程中自定义事务方法，其他读写分离仍然是原链接
 */
@Configuration
@EnableTransactionManagement(order = 2)
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {
    static Logger log = LoggerFactory.getLogger(DataSourceTransactionManager.class);

    @Resource
    private DataSource dataSource;

    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
     *
     * @return
     */
    @Bean(name = "transactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        log.info("-------------------- transactionManager init ---------------------");
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }
}