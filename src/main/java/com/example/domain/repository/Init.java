package com.example.domain.repository;

import java.lang.annotation.*;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/18 21:57
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/18 21:57
 * @UpdateRemark: 　1.自定义注解
 * @Version: 1.0
 * https://www.cnblogs.com/liangweiping/p/3837332.html
 * 注解的作用目标：
 *
 * 　　@Target(ElementType.TYPE)                      // 接口、类、枚举、注解
 *
 * 　　@Target(ElementType.FIELD)                     // 字段、枚举的常量
 *
 * 　　@Target(ElementType.METHOD)                 // 方法
 *
 * 　　@Target(ElementType.PARAMETER)            // 方法参数
 *
 * 　　@Target(ElementType.CONSTRUCTOR)       // 构造函数
 *
 * 　　@Target(ElementType.LOCAL_VARIABLE)   // 局部变量
 *
 * 　　@Target(ElementType.ANNOTATION_TYPE) // 注解
 *
 * 　　@Target(ElementType.PACKAGE)               // 包
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)// // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
public @interface Init {
    public String value() default "";
}
