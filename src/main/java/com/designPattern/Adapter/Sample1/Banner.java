package com.designPattern.Adapter.Sample1;

/**
 * @Description:交流100伏特类
 * @Author: gaoxi
 * @CreateDate: 2019/8/16 13:55
 * @Version: 1.0
 */
public class Banner {

    private String string;
    public Banner(String string){
        this.string = string;
    }
    /**
      * @Author: gxy 
      * @Description:IDEA自定义注释模板（主要解决params和return的问题）
      * @Date: 2019/8/16 
      * @return: void
     * https://blog.csdn.net/Aeve_imp/article/details/85785411
      **/
    public void showWithParen(){
      
        System.out.println("("+string+")");
    }
    public void showWithAster(){
        System.out.println("*"+string+"*");
    }
}