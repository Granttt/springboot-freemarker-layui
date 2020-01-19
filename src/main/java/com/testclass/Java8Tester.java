package com.testclass;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/31 13:50
 * @Description:Lambda 表达式实例
 * https://www.runoob.com/java/java8-lambda-expressions.html
 */
public class Java8Tester {
    public static void main(String[] args) {

//        new Thread(()->{//覆盖重写抽象方法
//            System.out.println(Thread.currentThread().getName()+"新线程创建了");
//            System.out.println("多线程任务执行！");
//        }).start();

        Java8Tester test = new Java8Tester();
        //类型声明
        MathOperation addition = (int a,int b) -> a+b;

        //不用类型声明
        MathOperation subtration = (a,b) -> a-b;

        //大括号中的返回语句
        MathOperation multiplication = (int a,int b) -> {return a*b;};

        //没有大括号及返回语句
        MathOperation division = (int a,int b) -> a/b;

        System.out.println("10 + 5 = " + test.operate(10,5,addition));
        System.out.println("10 - 5 = " + test.operate(10,5,subtration));
        System.out.println("10 * 5 = " + test.operate(10,5,multiplication));
        System.out.println("10 / 5 = " + test.operate(10,5,division));

        //不用括号
        GreetingService greetingService = message -> System.out.println("Hello " + message);

        //用括号
        GreetingService greetingService1 = (message) -> System.out.println("Hello " + message);

        greetingService.sayMessage("Runoob");
        greetingService1.sayMessage("Google");
    }
    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}