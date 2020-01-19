package com.thread.testone;

import java.util.concurrent.*;

public class ThreadPoolTestTwo implements Runnable{
    @Override
    public void run() {
        synchronized(this) {
            try{
                System.out.println("线程名称："+Thread.currentThread().getName());
                Thread.sleep(2000); //休眠是为了让该线程不至于执行完毕后从线程池里释放
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        BlockingQueue<Runnable>  queue = new ArrayBlockingQueue<>(4);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);
        for (int i = 0; i < 10; i++) {
            executor.execute(new Thread(new ThreadPoolTestTwo(), "TestThread".concat(""+i)));
            int threadSize = queue.size();
            System.out.println("线程队列大小为-->"+threadSize);
        }
        executor.shutdown();
    }
    /**
     * 可见，总共10个线程，因为核心线程数为2，2个线程被立即运行，线程队列大小为4，所以4个线程被加入队列，最大线程数为6，还能运行6-2=4个，
     * 其10个线程的其余4个线程又立即运行了。
     */
}
