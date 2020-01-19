package com.designPattern.Adapter.A2;

import java.io.IOException;

/**
 * @Description:
 * @Author: gaoxi
 * @CreateDate: 2019/8/17 19:01
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {
        FileIO f = new FileProperties();
        try {
            f.readFromFile("src/main/java/com/designPattern/Adapter/A2/file.txt");
            f.setValue("year", "2019");
            f.setValue("month", "8");
            f.setValue("day", "17");
            f.writeToFile("newfile.txt");

            //java中Class.getResource用法 https://www.cnblogs.com/keyi/p/6282838.html
            String basepath=Thread.currentThread().getContextClassLoader().getResource("").toString();
            if(System.getProperty("file.separator").equals("\\")){
                System.out.println(basepath.substring(6,basepath.length()));
            }else{
                System.out.println(basepath.substring(5,basepath.length()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}