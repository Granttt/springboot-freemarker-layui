package com.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/6/7 16:03
 * @Version: 1.0
 */
public class ServiceMain {
    public static void main(String[] args) {

        //调用load方法即可加载接口参数的所有实现
        ServiceLoader<DubboService> spiLoader = ServiceLoader.load(DubboService.class);
        //反射
        Iterator<DubboService> iteratorSpi = spiLoader.iterator();
        while (iteratorSpi.hasNext()){
            DubboService dubboService = iteratorSpi.next();
            dubboService.sayHello();;
        }
        for (DubboService dubboService : spiLoader) {
            dubboService.sayHello();
        }

    }
}