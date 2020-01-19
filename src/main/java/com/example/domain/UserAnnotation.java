package com.example.domain;

import com.example.domain.repository.Init;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/18 22:02
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/18 22:02
 * @UpdateRemark: 2.在数据模型使用注解
 * @Version: 1.0
 */
public class UserAnnotation {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    @Init(value = "liang")
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    @Init(value = "23")
    public void setAge(String age) {
        this.age = age;
    }
}