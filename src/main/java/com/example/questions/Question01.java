package com.example.questions;

/**
 * @Description:
 * @Author: gaoxi
 * @CreateDate: 2019/9/4 11:19
 * @Version: 1.0
 */
public class Question01 {
    /**
     * @param a
     * @param b
     * @return
     */
    public int div(int a, int b){
        try {
            return  a/b;
        }catch (ArithmeticException e){
            System.out.println("ArithmeticException");
        }catch (Exception e){
            System.out.println("Exception");
        }finally {
            System.out.println("finally");
        }
        return 0;
    }

    public static void main(String[] args) {
        Question01 q = new Question01();
        System.out.println("商是："+q.div(9,0));
    }
    /**
     * ArithmeticException
     * finally
     * 商是：0
     */
}