package com.example;

import com.example.utils.CommonRedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/11/1 19:35
 * @Description:RedisTemplate实现分布式锁
 * https://blog.csdn.net/weixin_38399962/article/details/82753763
 */
@RestController
public class CommonRedisHelperTest {
    @Autowired
    private CommonRedisHelper redisHelper;

    @RequestMapping("/redislock")
    public  void  redisLock(String key) throws InterruptedException {
        boolean lock = redisHelper.lock(key);
        if (lock) {
            // 执行逻辑操作
            //业务代码。。完成后主动删除锁
//            Thread.sleep(10000L);
//
//
//            redisHelper.delete(key);
        } else {
            // 设置失败次数计数器, 当到达5次时, 返回失败
            int failCount = 1;
            while(failCount <= 5){
                // 等待100ms重试
                 Thread.sleep(100L);

                if (redisHelper.lock(key)){
                    // 执行逻辑操作
                    redisHelper.delete(key);
                }else{
                    failCount ++;
                }
            }
            throw new RuntimeException("现在创建的人太多了, 请稍等再试");
        }

    }
}