package com.testclass;

/**
 * @Description: java类作用描述
 * @Author: gaoxi
 * @CreateDate: 2019/4/4 18:03
 * @UpdateUser: gxy
 * @UpdateDate: 2019/4/4 18:03
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class testclass {
    public static void changeStr(String str){
        str="welcome";
    }
    public static void changeObject(ObjectClass object){
        object=new ObjectClass("welcome");
    }

    public static void main(String[] args) {
        String str="1234";
        changeStr(str);
        System.out.println("StringValue:"+str);

        ObjectClass object= new ObjectClass(str);
        changeObject(object);
        System.out.println("ObjectVale:"+object.value);
    }
}
class ObjectClass{
    String value=null;
    public ObjectClass(final String value){
        this.value=value;
    }
}