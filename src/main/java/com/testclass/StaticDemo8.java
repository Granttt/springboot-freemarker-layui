
package com.testclass;
/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/4 18:52
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/4 18:52
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class StaticDemo8 {
    public static void main(String[] args) {
//        F f= new F();
        System.out.println(E.i);
        System.out.println(E.j);
    }
}
class E{
    static  E e=new E();//1
    static int i = 5;  //3
    static int j;      //4
    static {           //5
        E.i++;
        E.j++;
    }
    //构造方法只有在类被实例化（也就是类加载的使用阶段，类的生命周期分为装载、连接、初始化、使用和卸载的五个过程）的时候才会调用：new E();使用之前的阶段不会被调用
    //构造函数是在这个类要生成对象的时候才会加载并调用
    public  E(){        //2
        i++;
        j++;
        System.out.println("E:"+E.i);
        System.out.println("E:"+E.j);
    }
}
class F extends E{
}
/**
 * ①首先进行解析 i = 0,j = 0 //标记值
 *
 * ②停止静态的类加载，执行构造器(2处)中的方法，标记值发生了运算，i = 1,j = 1
 *
 * ③执行到3处，i = 5
 *
 * ④执行到4处j没有赋值，默认使用标记值1
 *
 * ⑤执行静态代码块(5处)i = 6，j = 2
 * 如果E e=new E();为非静态方法，则下面的E()g构造方法不会被加载，因为没有调用
 */
