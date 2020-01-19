package com.designPattern.Adapter.Sample3;

/**
 * @Description:对象适配器源码
 * @Author: gaoxi
 * @CreateDate: 2019/8/17 0:43
 * @Version: 1.0
 * 需要一个三孔插座，而现只有一个两孔插座
 */
public class TwoSocketAdapteeObj {//现成的类，有个两孔插座
    public void two() {
        System.out.println("这是两孔插座");
    }
}
interface SocketTargetObj {//目标接口，需要一个三孔插座
    public void three();
}
class SocketAdapterObj implements SocketTargetObj{

    TwoSocketAdapteeObj tsa;

    public SocketAdapterObj(TwoSocketAdapteeObj tsa) {
        this.tsa = tsa;
    }

    @Override
    public void three() {
        tsa.two();//只是包装了两孔插座去实现三孔插座
    }
}
class ClientObj{
    public static void main(String[] args) {
        TwoSocketAdapteeObj tsa = new TwoSocketAdapteeObj();
        SocketTargetObj st = new SocketAdapterObj(tsa);
        st.three();
        System.out.println();
    }
}