package com.jvm;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/11 16:14
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/11 16:14
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class jvmTest01 {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("虚拟机中试图使用的最大的内存是（最大分配）：" + maxMemory / (double)1024 / 1024 + "MB");
        System.out.println("虚拟机的总内存(初始分配)："+totalMemory/(double)1024/1024+"MB");
    }
}