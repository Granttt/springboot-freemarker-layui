package com.spi;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/6/7 16:00
 * @Version: 1.0
 * 步骤二
 */
public class RedService implements DubboService {
    @Override
    public void sayHello() {
        System.out.println("我是RedService服务实现");
    }
}