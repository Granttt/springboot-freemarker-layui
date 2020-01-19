package com.example;

import com.example.domain.UserAnnotation;
import com.example.domain.repository.UserAnnotationFactory;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/18 22:30
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/18 22:30
 * @UpdateRemark: 4.运行的代码
 * @Version: 1.0
 * https://www.cnblogs.com/liangweiping/p/3837332.html
 */
public class AnnotationTest {
    public static void main(String[] args) {
        UserAnnotation user = UserAnnotationFactory.creat();
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }
}