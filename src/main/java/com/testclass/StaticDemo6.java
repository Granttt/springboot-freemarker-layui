package com.testclass;

/**
 * @Description: 先父类后子类，先静态后动态，属性和代码块的初始化遵循正常的出场顺序无论是静态还是动态，但是他们总是先于构造器执行。但是还是需要通过题目的学习来加深我们的理解。
 * @Author: gaoxi
 * @CreateDate: 2019/4/4 18:29
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/4 18:29
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 * https://blog.csdn.net/qq_34993631/article/details/82890187
 */
public class StaticDemo6 {
    public static void main(String[] args) {
        new SB();
    }

}
class SA {

    D d;

    public SA() {
        System.out.println("A 3"); // 10.此时调用完了自己的非静态代码块来到了构造器
    }

    static {
        System.out.println("A 1"); // 1.先从父类的非静态开始
    }

    {
        System.out.println("A 2"); // 5.此处开始new对象（非静态相关）,非静态比构造方法先调用，与代码顺序无关
        d = new D();// 6.顺序执行
    }
}

class SB extends SA {
    static C c = new C(); // 2.调用完了父类的静态相关来到子类的静态相关

    static {
        System.out.println("B 1"); // 4.接着按照顺序来调用自己的静态代码块 ，到此子类的所有静态都执行完毕接下来将会执行非静态相关
    }

    {
        System.out.println("B 2"); // 11.父类的构造器调用完成调用子类的非静态块
    }

    public SB() {
        System.out.println("B 3"); // 12.调用完了自己的非静态块调用自己的构造方法
    }

}

class C {
    public C() {
        System.out.println("C"); // 3.C没有父类与静态直接调用自己的构造器  // 8.
    }
}

class D extends C {// 7. 来到了D但是D有自己的父类所以到达C类

    public D() {
        System.out.println("D");// 9.调用完了父类的构造器会来到子类的构造器
    }
}