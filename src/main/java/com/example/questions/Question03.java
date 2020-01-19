package com.example.questions;

/**
 * @Description:类的zhi'x
 * @Author: gaoxi
 * @CreateDate: 2019/9/9 15:57
 * @Version: 1.0
 * https://www.cnblogs.com/kyleinjava/p/8066913.html
 */
public class Question03 {

    public static  void changeStrA(String str){
        str = "welcome";
        System.out.println("changeStr:"+str);
    }
    public static  String changeStrB(String str){
        str = "welcome";
        System.out.println("changeStr:"+str);
        return  str;
    }
    public static  void chageObject(ObjectClass objectClass){
        objectClass = new ObjectClass("welcome");
        System.out.println("chageObject:"+objectClass.value);
    }

    public static void main(String[] args) {
        String str = "1234";
        changeStrA(str);
//        str = changeStrB(str);
        System.out.println("StringValue:"+str);
        System.out.println("===================================");

        ObjectClass objectClass = new ObjectClass(str);
        chageObject(objectClass);
        System.out.println("ObjectValue:"+objectClass.value);
    }
}
class ObjectClass{
    String value = null;

    public ObjectClass(final String value) {
        this.value = value;
    }
}