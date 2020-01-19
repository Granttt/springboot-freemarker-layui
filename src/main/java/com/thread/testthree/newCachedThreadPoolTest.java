package com.thread.testthree;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java四种线程池的示例
 * newCachedThreadPool
 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
 * https://blog.csdn.net/qq_31441667/article/details/78830395
 */
@SuppressWarnings("AlibabaThreadPoolCreation")
public class newCachedThreadPoolTest {
    @SuppressWarnings("AlibabaThreadPoolCreation")
    public static void main(String[] args) {

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final  int index = i;
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //启用cachedThreadPool线程池,创建时里面的线程每个睡眠1s,而上面的for创建新任务(线程)时每个只睡眠0.2s
                        //所以当睡眠0.2秒后执行到execute()方法时,cachedThreadPool里面的线程还有0.8s的睡眠;没有多余线程所以只能在
                        //cachedThreadPool线程池新建线程,1000/0.2=5,所以cachedThreadPool线程池创建5个线程之后,第一个线程才执行完毕
                        //才能复用线程.通过调整睡眠时间可以得出不同的结果，
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /**
                     * 线程池为无限大，当执行第二个任务时第一个任务已经完成，
                     * 会复用执行第一个任务的线程，而不用每次新建线程。
                     * 所以打印的线程名字一直是pool-1-thread-1
                     * 通过调整睡眠时间可以得出不同的结果，
                     */
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
class newCachedThreadPoolTestTwo {
    public static void main(String[] args) {
        ExecutorService singleThreadExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            System.out.println(index);
                            Thread.sleep(10 * 1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
