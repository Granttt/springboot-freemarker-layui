package com.testclass;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/11 19:08
 * @Description:官网来源
 * https://plugins.jetbrains.com/plugin/10065-mybatis-log-plugin
 */
public class MybatisLogPlugin {
    public static void main(String[] args) {
        System.out.println("selectSql1 - ==>  Preparing:select * from t_table where name = ?");
        System.out.println("selectSql1 - ==>  Parameters:hello(String)");

        System.out.println("selectSql2 - ==>  Preparing:update t_table set name = ? where id =?");
        System.out.println("selectSql2 - ==>  Parameters:world(String),123(Integer)");
    }
}