/**
 * 
 */
package com.example;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @Project:spring-boot-sample-helloworld  
 * @Class:ScheduledService 
 * @author 高希阳
 * @date 2018-8-30 上午10:36:50   
 * @version 1.0.0 
 * @Description: 使用Spring Task简单的定时任务
 */
@Slf4j
@Component
public class ScheduledService {
	
	protected static Logger log=LoggerFactory.getLogger(ScheduledService.class);
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/*
    @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
    @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
    @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
    @Scheduled(cron="0/5 * * * * ?") ：通过cron表达式定义规则
    */
	
	@Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
//        log.info("=====>>>>>使用cron  {}",dateformat.format(System.currentTimeMillis()));
    }
    @Scheduled(fixedRate = 5000)//上一次开始执行时间点之后5秒再执行
    public void scheduled1() {
//        log.info("=====>>>>>使用fixedRate  {}", dateformat.format(System.currentTimeMillis()));
    }
    @Scheduled(fixedDelay = 5000)//上一次执行完毕时间点之后5秒再执行
    public void scheduled2() {
//        log.info("=====>>>>>fixedDelay  {}",dateformat.format(System.currentTimeMillis()));
    }
    @Scheduled(initialDelay=1000, fixedRate=6000)//第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
    public void scheduled3() {
//    	log.info("=====>>>>>initialDelay  {}",dateformat.format(System.currentTimeMillis()));
    }
}
