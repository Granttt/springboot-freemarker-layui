package com.testclass;

/**
 * @Description: 三目运算
 * @Author: gaoxi
 * @CreateDate: 2019/6/5 22:18
 * @Version: 1.0
 * 关于三目运算符的右结合性
 * a?b:c?d:e,这行代码应该等同于a?b:(c?d:e),两个三目运算符，优先级相同，优先级相同时，从右往左结合。但是运算还是从左往右运算。结合和运算要区分开来
 */
public class TernaryOperator {
    public static void main(String[] args) {
        int i = -2;
        int n = ++i == 0 ? 99 : i == -1 ? 11 : 22;
        System.out.println(n);

        /**
         * https://ask.csdn.net/questions/381344
         */
        String s = "abc";
        int y = 1;
        System.out.println("abc".equals(s) ? 1 : (++y) == 2 ? 0 : -1);
        //根据右结合等价于 "abc".equals(s) ? 1 : ((++y) == 2 ? 0 : -1)
        // a ? b : c ? d : e  将被解析为  a ? b : ( c ? d : e )
        /**
         * 总结：
         * 结合----结合性----右结合性（优先级相同时，进行右结合）
         * 运行----逻辑运算----从左向右
         */
        System.out.println(y);


        /**
         * https://blog.csdn.net/qq_37609701/article/details/79845094
         */
        int a = 1, b = 10;
        int temp1 = 0 < 1 ? a++ : b++;
        System.out.println("题1:");
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("temp1 = " + temp1);

        int c = 1, d = 10;
        int temp2 = 0 < 1 ? c = c + 1 : d + 1;
        System.out.println("题2:");
        System.out.println("c = " + c + ", d = " + d);
        System.out.println("temp2 = " + temp2);

        int e = 1, f = 10;
        int temp3 = 0 > 1 ? e = e + 1 : f + 1;
        System.out.println("题3:");
        System.out.println("e = " + e + ", f = " + f);
        System.out.println("temp3 = " + temp3);

        /**
         * 【Java】i++，++i与i+1的区别
         * https://blog.csdn.net/crab0314/article/details/79193795
         */
        int x = 10;
        int z = x+1;
        System.out.println(z);
        System.out.println(x);
        System.out.println("========================");
        z=x++;
        System.out.println(z);
        System.out.println(x);
        System.out.println("========================");
        z = ++x;
        System.out.println(z);
        System.out.println(x);



    }
}