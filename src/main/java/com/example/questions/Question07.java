package com.example.questions;

/**
 * @Description:ThreadLocal 是什么？有哪些使用场景？
 * @Author: gaoxi
 * @CreateDate: 2019/9/22 17:40
 * @Version: 1.0
 * https://blog.csdn.net/meism5/article/details/90413860
 * ThreadLocal 是线程本地存储，在每个线程中都创建了一个 ThreadLocalMap 对象，每个线程可以访问自己内部 ThreadLocalMap 对象内的 value。
 */
public class Question07 {
    //线程本地存储变量
    private static final ThreadLocal<Integer> THREAD_LOCAL_NUM = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    //打印结果：启动了 3 个线程，每个线程最后都打印到 "ThreadLocal num=5"，而不是 num 一直在累加直到值等于 15
    public static void main(String[] args) {
        //启动三个线程
        for (int i = 0; i < 3; i++) {
            Thread t =new Thread(){
                @Override
                public void run() {
                    add10ByThreadLocal();
                }
            };
            t.start();
        }
    }
    /**
     * 线程本地存储变量加 5
     */
    private static void add10ByThreadLocal(){
        for (int i = 0; i < 5; i++) {
            Integer n = THREAD_LOCAL_NUM.get();
            n += 1;
            THREAD_LOCAL_NUM.set(n);
            System.out.println(Thread.currentThread().getName() + " : ThreadLocal num=" + n);
        }
    }
}