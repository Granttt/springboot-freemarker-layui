package com.thread.testone;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池学习
 * 示例一，LinkedBlockingQueue<Runnable>队列使用
 * https://www.cnblogs.com/feiyun126/p/7692068.html
 */
public class ThreadPoolTest implements Runnable{
    @Override
    public void run() {
        synchronized (this){
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2,6,1, TimeUnit.DAYS,queue);

        for (int i=0; i<10; i++){
            executor.execute(new Thread(new ThreadPoolTest(),"TestThread".concat(""+i)));
            int threadSize = queue.size();
            System.out.println("线程队列大小为-->"+threadSize);
        }
        executor.shutdown();
    }
}
