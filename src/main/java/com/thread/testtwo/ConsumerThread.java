package com.thread.testtwo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 消费线程
 */
public class ConsumerThread extends Thread{
    private ArrayBlockingQueue queue;
    public ConsumerThread(ArrayBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("准备消费");
        int taskNum;
        try {
            //queue.take()移除并返回BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到Blocking有新的对象被加入为止
            taskNum = (int) queue.take();
            System.out.println("消费了"+taskNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
