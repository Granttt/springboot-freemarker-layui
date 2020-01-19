package com.designPattern.proxy.staticProxy;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/13 23:26
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/13 23:26
 * @UpdateRemark: 被代理类（委托人）
 * @Version: 1.0
 */
public class RealStar implements Star{

    @Override
    public void confer() {
        System.out.println("RealStar.confer");
    }

    @Override
    public void signContract() {
        System.out.println("RealStar.signContract");
    }

    @Override
    public void bookTicket() {
        System.out.println("RealStar.bookTicket");
    }

    @Override
    public void sing() {
        System.out.println("RealStar(周杰伦).confer");
    }

    @Override
    public void collectMoney() {
        System.out.println("RealStar.collectMoney");
    }
}