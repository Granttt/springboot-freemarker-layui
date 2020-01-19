package com.designPattern.proxy.staticProxy;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/13 23:31
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/13 23:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class Client {
    public static void main(String[] args) {
        Star real = new RealStar();
        ProxyStar proxyStar =new ProxyStar(real);
        proxyStar.bookTicket();
        proxyStar.collectMoney();
        proxyStar.confer();
        proxyStar.signContract();

        proxyStar.sing();

    }



}