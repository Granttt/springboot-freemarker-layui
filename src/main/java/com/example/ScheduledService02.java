package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

/**
 * @Description: 关于@schedule任务前，下次是否启动实测
 * @Author: gaoxi
 * @CreateDate: 2019/7/8 22:21
 * @Version: 1.0
 * 首先给出结论：上次未结束，下次任务不会启动。它会自动顺延到任务结束后的，下一个周期触发。
 * https://blog.csdn.net/ye17186/article/details/88057635
 */
public class ScheduledService02 {
    protected static Logger log= LoggerFactory.getLogger(ScheduledService02.class);

    /**
     * 任务设定的是，从0秒开始，每3秒钟执行一次。任务耗时通过线程sleep来模拟，时长则是一个0-10的随机数。
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void run() {

        int sleep = new Random().nextInt(10);
        log.info("say sleep: {}s", sleep);
        try {
            Thread.sleep(sleep * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}