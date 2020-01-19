package com.example.questions;

/**
 * @Description:CAS乐观锁
 * @Author: gaoxi
 * @CreateDate: 2019/9/20 15:31
 * @Version: 1.0
 * https://mp.weixin.qq.com/s?__biz=MzU3NTA5MzQ5Mg==&mid=2247483942&idx=1&sn=3dfb618c00897826e030507f7f5b3571&chksm=fd292dbdca5ea4ab3c54da86e562eaf925b49866e70ce62c4032760b3d6554e5d869f029f5c4&scene=21#wechat_redirect
 */
public class question06 {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int expectedValue = cas.get();
                    System.out.println(expectedValue);
                    boolean b = cas.compareAndSet(expectedValue, (int)(Math.random() * 101));
                    System.out.println(b);
                    System.out.println("=====================================================");
                }
            }).start();
        }
    }
}
class CompareAndSwap{
    private int value;

    //获取内存值
    public synchronized int get(){
        return value;
    }

    //比较
    public synchronized int compareAndSwap(int expectedValue, int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }

    //设置
    public synchronized boolean compareAndSet(int expectedValue, int newValue){
                return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}