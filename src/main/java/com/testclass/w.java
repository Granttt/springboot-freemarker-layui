package com.testclass;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/4 19:25
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/4 19:25
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class w {
    public static void main(String[] args) {
//        Y y= new Y();
//        System.out.println(new Y().i);
//        System.out.println(Y.j);
        System.out.println(new Y().y);
    }

}
class Y{
    Y y;
    int i=67;
    static  int j=68;
    public Y(){
        System.out.println("yyyyyyyyyyyyyyyy");
    }
}