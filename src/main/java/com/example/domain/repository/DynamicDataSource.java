package com.example.domain.repository;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:数据源路由类
 * @Author: gaoxi
 * @CreateDate: 2019/10/12 11:11
 * @Version: 1.0
 * https://www.iteye.com/blog/412887952-qq-com-2303075
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /*
     * 代码中的determineCurrentLookupKey方法取得一个字符串，
     * 该字符串将与配置文件中的相应字符串进行匹配以定位数据源，配置文件，即applicationContext.xml文件中需要要如下代码：(non-Javadoc)
     * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
     */
    @Override
    protected Object determineCurrentLookupKey() {
        /*
         * DynamicDataSourceContextHolder代码中使用setDataSourceType
         * 设置当前的数据源，在路由类中使用getDataSourceType进行获取，
         */
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}