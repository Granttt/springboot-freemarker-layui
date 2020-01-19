package com.designPattern.Adapter.Sample2;

/**
 * @Description:
 * @Author: gaoxi
 * @CreateDate: 2019/8/17 0:11
 * @Version: 1.0
 */
public class PrintBanner extends Print{
    private Banner banner;

    public PrintBanner(String string) {
        this.banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}