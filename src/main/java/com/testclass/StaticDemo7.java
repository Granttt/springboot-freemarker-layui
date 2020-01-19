package com.testclass;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/4 18:45
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/4 18:45
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class StaticDemo7 {
    public static void main(String[] args) {
        //new SDemo();
        System.out.println(SDemo.i);
    }
}

class SDemo{
    static {
        System.out.println(++SDemo.i);//1
    }
    static int i=5;//2
}
/*
①进行解析，i = 0; // 这里是标记值

②按照静态出现的顺序来加载。执行到1处，此时i的标记值发生了运算它改为了1。

③执行到2处检查i是否赋值，结果将5赋值给了i

④i的最终值为5。
 */