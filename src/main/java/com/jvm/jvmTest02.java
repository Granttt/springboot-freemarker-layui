package com.jvm;

import java.util.Vector;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/11 23:06
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/11 23:06
 * @UpdateRemark: 模拟OOM异常
 * @Version: 1.0
 */
public class jvmTest02 {
    public static void main(String[] args) {
        //-Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/jvmTest02.dump
        //堆内存溢出
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            //连续申请5m内存
            v.add(new Byte[1*1024*1024]);
        }
    }
}