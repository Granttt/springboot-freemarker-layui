package com.designPattern.Adapter.Sample2;

/**
 * @Description:
 * @Author: gaoxi
 * @CreateDate: 2019/8/16 14:30
 * @Version: 1.0
 */
public class Main {
    /**
      * @Author: gxy
      * @Description:
      * @Date: 2019/8/16
      * @Param args:
      * @return: void
      **/
    public static void main(String[] args) {
        Print p = new PrintBanner("hello");
        p.printWeak();
        p.printStrong();
        System.out.println();
    }
}