package com.testclass;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/27 14:13
 * @Description:判断是否是基本数据类型
 * https://blog.csdn.net/lightofsms/article/details/86715129
 */
public class BaseTypeTest {
    public static void main(String[] args) {
        System.out.println();

        String a = "abc";
        Integer b = 1;
        List list = new ArrayList();
        System.out.println(isBaseType(a.getClass(),true));
        System.out.println(isBaseType(b.getClass(),false));
        System.out.println(isBaseType(list.getClass(),false));
    }

    /**
     * 判断对象属性是否是基本数据类型,包括是否包括string
     * @param className
     * @param incString 是否包括string判断,如果为true就认为string也是基本数据类型
     * @return
     */
    public static boolean isBaseType(Class className,boolean incString){
        if (incString && className.equals(String.class)){
            return true;
        }
        return className.equals(Integer.class) ||
                className.equals(int.class) ||
                className.equals(Byte.class) ||
                className.equals(byte.class) ||
                className.equals(Long.class) ||
                className.equals(long.class) ||
                className.equals(Double.class) ||
                className.equals(double.class) ||
                className.equals(Float.class) ||
                className.equals(float.class) ||
                className.equals(Character.class) ||
                className.equals(char.class) ||
                className.equals(Short.class) ||
                className.equals(short.class) ||
                className.equals(Boolean.class) ||
                className.equals(boolean.class);
    }
}