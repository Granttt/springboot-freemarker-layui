package com.example.domain.repository;

import com.example.domain.UserAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/18 22:03
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/18 22:03
 * @UpdateRemark: 3.用“构造工厂”充当“注解解析器”
 * @Version: 1.0
 */
public class UserAnnotationFactory {
    public static UserAnnotation creat(){
        UserAnnotation user = new UserAnnotation();

        //获取UserAnnotation类中所有的方法（getDeclaredMethods也行）
        Method[] methods = UserAnnotation.class.getMethods();
        for (Method method : methods) {
            // 如果此方法有注解，就把注解里面的数据赋值到user对象
            if (method.isAnnotationPresent(Init.class)){
                Init init = method.getAnnotation(Init.class);
                try {
                    // invoke(从中调用基础方法的对象,用于方法调用的参数)
                    /**
                     * invoke(Object obj, Object... args)
                     * args是数组，invoke的作用是让obj循环调用args里的方法
                     * invoke方法中的obj就是一个实例化对象，可以直接返回对象的中每个属性的值。
                     * 加上后面的agrs结果是一样的。
                     * 通过调用invoke方法来执行对象的某个方法
                     */
                    Object obj = method.invoke(user,init.value());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return user;
    }
}