package com.thread.testthree;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
 */
public class newScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        //表示延迟3秒执行
        for (int i = 0; i < 10; i++) {
            scheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.out.println("delay 3 seconds--"+Thread.currentThread().getName());
                }
            }, 3, TimeUnit.SECONDS);

        }
        scheduledThreadPool.shutdown();
    }
}

/**
 * 定期执行示例
 */
class newScheduledThreadPoolTestTwo{
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPoolw = Executors.newScheduledThreadPool(5);
            //延迟4s后,每2s执行一次
            scheduledThreadPoolw.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    System.out.println("delay 1 seconds, and excute every 3 seconds");
                }
            }, 4, 2, TimeUnit.SECONDS);

    }

}
