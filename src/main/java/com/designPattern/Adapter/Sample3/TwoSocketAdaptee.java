package com.designPattern.Adapter.Sample3;

/**
 * @Description:类适配器源码
 * @Author: gaoxi
 * @CreateDate: 2019/8/17 0:23
 * @Version: 1.0
 * 被适配者
 */
public class TwoSocketAdaptee {
    public void two(){
        System.out.println("两孔插座");
    }
}
/**
  * @Author: gxy
  * @Description:不仅扩展了接口，而且对适配者 进行了覆盖
  * @Date: 2019/8/17
  * @Param null:
  * @return: null
 * 目标对象
  **/
interface SocketTarget{
    public void three();
    public void twoNow();
}

/**
  * @Author: gxy
  * @Description:适配器
  * @Date: 2019/8/17
  * @Param null:
  * @return: null
  **/
class SocketAdapter extends TwoSocketAdaptee implements SocketTarget{

    @Override
    public void three() {
        System.out.println("这是三孔插座");
    }

    @Override
    public void twoNow() {
        super.two();
    }
}
class Client {
    public static void main(String[] args) {
        SocketTarget socketTarget = new SocketAdapter();
        socketTarget.three();
        socketTarget.twoNow();
    }
}