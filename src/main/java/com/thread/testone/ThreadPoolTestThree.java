package com.thread.testone;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  java.util.concurrent.BlockingQueue 类有有三种方法将线程添加到线程队列里面
 *  public boolean add(E e) 方法将抛出IllegalStateException异常，说明队列已满。
 *
 * public boolean offer(E e) 方法则不会抛异常，只会返回boolean值，告诉你添加成功与否，队列已满，当然返回false。
 *
 * public void put(E e) throws InterruptedException 方法则一直阻塞（即等待，直到线程池中有线程运行完毕，可以加入队列为止）
 *
 * https://www.cnblogs.com/feiyun126/p/7692068.html
 */
public class ThreadPoolTestThree implements Runnable{
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
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4); //固定为4的线程队列
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 6, 1, TimeUnit.DAYS, queue);

        for (int i = 0; i < 10; i++) {
            executor.execute(new Thread(new ThreadPoolTestThree(), "TestThread".concat(""+i)));
            int threadSize = queue.size();
            System.out.println("线程队列大小为-->"+threadSize);
            if (threadSize==4){
                queue.add(new Runnable() {  //队列已满，抛异常
                    @Override
                    public void run(){
                        System.out.println("我是新线程，看看能不能搭个车加进去！");

                    }
                });
            }
        }
    }
}
