package com.designPattern.Adapter.Sample1;

/**
 * @Description:适配器类
 * @Author: gaoxi
 * @CreateDate: 2019/8/16 14:25
 * @Version: 1.0
 */
public class PrintBanner extends Banner implements Print{
    /**
      * @Author: gxy
      * @Description:
      * @Date: 2019/8/16
      * @Param string:
      * @return: null
      **/
    public PrintBanner(String string) {
        super(string);
    }

    @Override
    public void printWeak() {
        showWithParen();
    }

    @Override
    public void printStrong() {
        showWithAster();
    }
}