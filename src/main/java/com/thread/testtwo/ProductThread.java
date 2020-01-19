package com.thread.testtwo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 生产线程
 */
public class ProductThread extends  Thread{
    private int taskNum;//任务数量
    private ArrayBlockingQueue queue;//有界队列
    public ProductThread(int taskNum,ArrayBlockingQueue queue) {
        this.taskNum = taskNum;
        this.queue = queue;
    }

    @Override
    public void run() {
        //模拟生产
        try {
            Thread.currentThread().sleep(5000);
            System.out.println("开始生产");
            //把taskNum加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则报异常
            queue.add(taskNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
