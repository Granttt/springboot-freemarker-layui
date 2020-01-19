package com.example.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description:如何实现数组和 List 之间的转换？
 * @Author: gaoxi
 * @CreateDate: 2019/9/18 14:52
 * @Version: 1.0
 */
public class Question05 {
    /**
     * 数组转 List：使用 Arrays. asList(array) 进行转换。
     * List 转数组：使用 List 自带的 toArray() 方法。
     */
    public static void main(String[] args) {
        //list to array
        List<String> list = new ArrayList<>();
        list. add("☞精◈彩◈猿◈笔◈记☜");
        list. add("的博客");
        /**
         * 如果没有括号中的new String[0] 会报错
         * [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
         * 参考：https://blog.csdn.net/linke1183982890/article/details/82756692
         */
        String[] strings = (String[]) list.toArray(new String[0]);
        System.out.println(strings[0]);
        //array to list
        String[] array = new String[]{"☞精◈彩◈猿◈笔◈记☜","的博客"};
        List<String> listArray = Arrays.asList(array);
        System.out.println(listArray.get(1));

        /**
         * 用 Collections.reverse 反转一个 List
         * https://www.cnblogs.com/ywb2018/p/9922829.html
         */
        List<String> list2 = new ArrayList<String>();
        list2.add( "11" );
        list2.add( "12" );
        list2.add( "13" );
        System.out.println(list2);

        // 反转 list
        Collections.reverse( list2 );
        System.out.println("反转 list:"+list2);
    }
}