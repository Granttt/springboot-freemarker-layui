package com.designPattern.Adapter.A2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description:
 * @Author: gaoxi
 * @CreateDate: 2019/8/17 18:53
 * @Version: 1.0
 */
public class FileProperties extends Properties implements FileIO{
    @Override
    public void readFromFile(String filename) throws IOException {
        //从InputStreamz中取出属性集合
        load(new FileInputStream(filename));
    }

    @Override
    public void writeToFile(String filename) throws IOException {
        //向OutputStream中写入属性集合，"written by FileProperties"部分为注释文字
        store(new FileOutputStream(filename),"written by FileProperties");
    }

    @Override
    public void setValue(String key, String value) {
        //设置属性值
        setProperty(key,value);
    }

    @Override
    public String getValue(String key) {
        //读取属性值
        return getProperty(key,"");
    }
}