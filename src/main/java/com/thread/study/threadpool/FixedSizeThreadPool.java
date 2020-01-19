package com.thread.study.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description: 手写实现线程池
 * @Author: gaoxi
 * @CreateDate: 2019/4/28 22:15
 * @Version: 1.0
 */
public class FixedSizeThreadPool {

    /**
     * 1.需要一个任务仓库
     */
    private BlockingQueue<Runnable> blockingQueue;
    /**
     * 2.需要一个线程的集合（卡车），卡车从仓库中拉取任务执行
     */
    private List<Thread> workers;
    /**
     * 3.需要一个人来干活
     */
    public static class Worker extends Thread{
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }
        @Override
        public void run() {
            //希望他能不断的到仓库中去拿东西
            //7.b
            while (this.pool.isWorking || this.pool.blockingQueue.size()>0){
                Runnable task = null;
                try {
                    //7.c
                    if (this.pool.isWorking){
                        //take(): 阻塞方法，从队列中获取值，如果队列中没有值，线程会一直阻塞，直到队列中有值，并且该方法取得了该值。
                        task = this.pool.blockingQueue.take();
                    }else {
                        //poll()：非阻塞方法，从队列中获取值，如果没有取到会抛出异常。
                        task = this.pool.blockingQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null){
                    task.run();
                    System.out.println("线程："+Thread.currentThread().getName()+"执行");
                }
                if (this.pool.blockingQueue.size()==0){
                    System.out.println("线程全部执行完毕");
                }
            }
        }
    }
    /**
     * 4.需要进行线程池的初始化-规定仓库大小和集合大小，可以把线程准备就绪
     */
    public FixedSizeThreadPool(int poolSize,int taskSize) {
        if (poolSize <= 0 || taskSize <= 0){
            throw new IllegalArgumentException("非法参数异常");
        }
        this.blockingQueue = new LinkedBlockingQueue<>(taskSize);
        //构造线程安全的线程集合
        this.workers = Collections.synchronizedList(new ArrayList<Thread>());
        for (int i=0;i<poolSize;i++){
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }
    /**
     * 5.需要向仓库中放任务（非阻塞）
     */
    public boolean submit(Runnable task){
        //7.a
        if (isWorking){//没有关闭
            /**
             * offer(E e): 将给定的元素设置到队列中，如果设置成功返回true, 否则返回false. e的值不能为空，否则抛出空指针异常。
             * 有返回值，非阻塞方法
             */
            return this.blockingQueue.offer(task);
        }else {
            return false;
        }
    }
    /**
     * 6.需要像仓库中放任务（阻塞）
     */
    public void execute(Runnable task) throws InterruptedException {
        if (isWorking){
            this.blockingQueue.put(task);
        }
    }
    /**
     * 7.需要一个关闭方法
     * a.关闭的时候，仓库要停止有新的任务进来
     * b,关闭的时候，仓库里如果还有任务，则要执行完毕
     * c.关闭的时候，如果再去仓库拿东西，则此时不应该再阻塞了
     * d.关闭的时候，如果还有线程被阻塞（未添加进仓库），则我们要强行中断
     */
    private volatile boolean isWorking = true;//默认未关闭,volatile保证数据可见性
    public void shutDown(){
        this.isWorking = false;

        for (Thread thread:workers){
            if (thread.getState().equals(Thread.State.WAITING) ||
                        thread.getState().equals(Thread.State.BLOCKED)){
                thread.interrupt();//7.d 中断线程方法
            }
        }
    }
}