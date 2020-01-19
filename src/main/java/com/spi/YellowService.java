package com.spi;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/6/7 16:02
 * @Version: 1.0
 * 步骤三
 */
public class YellowService implements DubboService {

    @Override
    public void sayHello() {
        System.out.println("我是YellowService服务实现");
    }
}