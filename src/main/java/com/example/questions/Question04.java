package com.example.questions;

/**
 * @Description:两个对象的 hashCode() 相同，则 equals() 也一定为 true，对吗？
 * @Author: gaoxi
 * @CreateDate: 2019/9/17 17:21
 * @Version: 1.0
 */
public class Question04 {

    public static void main(String[] args) {
        String str1 = "Aa";
        String str2 = "BB";
        System.out.println(String.format("str1：%d | str2：%d", str1. hashCode(),str2. hashCode()));
        System. out. println(str1. equals(str2));
        /**
         * 不对，两个对象的 hashCode() 相同，equals() 不一定 true。
         * 因为在散列表中，hashCode() 相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。
         */
        System.out.println("Aa".hashCode()); // 2112
        System.out.println("BB".hashCode()); // 2112
        System.out.println("ABCDEa123abc".hashCode()); // 165374702
        System.out.println("ABCDFB123abc".hashCode()); // 165374702

        /**
         * 如何将字符串反转？
         * 使用 StringBuilder 或者 stringBuffer 的 reverse() 方法。
         */
        //StringBuffer reverse
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("abcdefg");
        System.out.println(stringBuffer.reverse());//gfedcba

        //StringBuilder reverse
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("abcdefg");
        System.out.println(stringBuilder.reverse());//gfedcba
    }
}