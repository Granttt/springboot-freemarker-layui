package com.designPattern.Adapter.A2;

import java.io.IOException;

/**
 * @Description:
 * @Author: gaoxi
 * @CreateDate: 2019/8/17 18:52
 * @Version: 1.0
 */
public interface FileIO {
    public void readFromFile(String filename) throws IOException;
    public void writeToFile(String filename) throws IOException;
    public void setValue(String key, String value);
    public String getValue(String key);
}