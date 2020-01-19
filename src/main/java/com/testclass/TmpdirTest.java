package com.testclass;

/**
 * @auther: 高希阳
 * @Date: 2018/12/27 12:30
 * @Description:ystem.getproperty(“java.io.tmpdir”)是获取操作系统缓存的临时目录，不同操作系统的缓存临时目录不一样，
 * https://www.cnblogs.com/nbjin/p/7392541.html
 */
public class TmpdirTest {
    public static void main(String[] args) {
        //默认的临时文件路径
        System.out.println(System.getProperty("java.io.tmpdir"));
        //Java供应商的 URL
        System.out.println(System.getProperty("java.vendor.url"));
    }
}
