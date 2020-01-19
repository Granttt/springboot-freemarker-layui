package com.thread.study.threadpool;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/28 23:04
 * @Version: 1.0
 */
public class TestThreadPool {
    public static void main(String[] args) {
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3,6);
        for (int i=0;i<6;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("一个线程被放入了我们的仓库中");
                    try {
                        Thread.sleep(2500L);
                    } catch (InterruptedException e) {
                        System.out.println("有一个线程被唤醒了");
                    }
                }
            });
        }
        pool.shutDown();
    }
}