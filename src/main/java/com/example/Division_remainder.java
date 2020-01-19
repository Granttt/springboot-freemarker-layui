package com.example;

/**
 * @Author: gxy
 * @CreateDate: 2019/11/28 20:53
 * @Description:Java中的相除（/）和取余（%）
 * https://blog.csdn.net/sinat_41152339/article/details/81054379
 */
public class Division_remainder {
    public static void main(String[] args) {
        int a = (1 % 5)*10;
        int b = 15 % 5;
        int c = 5 / 13;
        int d = 5 % 13;
        int e = 13 / -5;
        int f = -13 / 5;
        int h = -13 % 5;
        int j = 13 % -5;
        int g = 10 % 3;
        System.out.println(a + "，" + b);
        System.out.println(c + "，" + d);
        System.out.println(e + "，" + f);
        System.out.println(h + "，" + j);
        System.out.println(g );
    }
}