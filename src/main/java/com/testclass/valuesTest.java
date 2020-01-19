package com.testclass;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/10/26 17:43
 * @Description:java使用省略号代替多参数（参数类型... 参数名）
 * 个数可变的参数
 * https://www.cnblogs.com/fnlingnzb-learner/p/5952716.html
 */
public class valuesTest {
    private static int sumUp(int...  values) {
        int sum = 0;
        for (int i = 0; i <  values.length; i++) {
            sum +=  values[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int sum = sumUp(1,2,3,4,5);
        System.out.println(sum);
    }
}