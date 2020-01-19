package com.designPattern.proxy.staticProxy;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/13 23:28
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/13 23:28
 * @UpdateRemark: 代理类（代理人、中介）。
 * @Version: 1.0
 */
public class ProxyStar implements Star {

    private Star star;

    public ProxyStar(Star star) {
        super();
        this.star = star;
    }

    @Override
    public void confer() {
        System.out.println("ProxyStar.confer");
    }

    @Override
    public void signContract() {
        System.out.println("ProxyStar.signContract");
    }

    @Override
    public void bookTicket() {
        System.out.println("ProxyStar.bookTicket");
    }

    /**
     * 代理人无法唱歌
     * 调用真正的歌手
     */
    @Override
    public void sing() {
        star.sing();
    }

    @Override
    public void collectMoney() {
        System.out.println("ProxyStar.collectMoney");
    }
}