package com.testclass;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/28 23:44
 * @Version: 1.0
 */
class Brid {
    {
        System.out.println("Brid1");
    }

    public Brid() {
        System.out.println("Brid2");
    }
}
class Raptor extends Brid{
    static {
        System.out.println("Raptor1");
    }

    public Raptor() {
        System.out.println("Raptor2");
    }
    {
        System.out.println("Raptor3");
    }
    static {
        System.out.println("Raptor4");
    }
}
class Hawk extends Raptor{
    public static void main(String[] args) {
        System.out.println("Hawk1");
        new Hawk();
        System.out.println("Hewa2");
    }
}