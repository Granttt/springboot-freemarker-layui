package com.testclass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/20 13:49
 * @Description:ListSetHashTest
 */
public class ListSetHashTest {
    public static void main(String[] args) {
        //list.removeAll
        List<String>list = new ArrayList<String>();
        list.add("保护环境");  //向列表中添加数据
        list.add("爱护地球");  //向列表中添加数据
        list.add("从我做起");  //向列表中添加数据
        List<String> list1 = new ArrayList<String>();
        list1.add("保护环境");  //向列表中添加数据
        list1.add("爱护地球");  //向列表中添加数据
        boolean ret = list.removeAll(list1);  //从list中移除与list1相同的元素
        Iterator<String> it = list.iterator();  //创建迭代器
        while(it.hasNext()){  //循环遍历迭代器
            System.out.println(it.next());  //输出集合中元素
        }


        //hashset.removeAll
        HashSet<String> sets = new HashSet<String>();
        sets.add("保护环境");  //向列表中添加数据
        sets.add("爱护地球");  //向列表中添加数据
        sets.add("从我做起");  //向列表中添加数据
        HashSet<String> sets1 = new HashSet<String>();
        sets1.add("保护环境");  //向列表中添加数据
        sets1.add("从我做起");  //向列表中添加数据
        boolean ret1 = sets.removeAll(sets1);  //从list中移除与list1相同的元素
        Iterator<String> it1 = sets.iterator();  //创建迭代器
        while(it1.hasNext()){  //循环遍历迭代器
            System.out.println(it1.next());  //输出集合中元素
        }
    }
}