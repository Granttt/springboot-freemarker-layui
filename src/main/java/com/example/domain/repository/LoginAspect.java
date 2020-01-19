package com.example.domain.repository;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/25 11:22
 * @Description:验证是否登录AOP切面
 */
@Aspect
@Component
public class LoginAspect {

    //定义切入点
    @Pointcut(value = "@annotation(com.example.domain.repository.Auth)")
    public void access(){}

    @Before("access()")
    public void before(){
        System.out.println("开始验证用户是否登录...");
    }

    //环绕增强
    @Around("@annotation(auth)")
    public Object around(ProceedingJoinPoint pj, Auth auth){
        /**
         * ProceedingJoinPoint 获取目标方法，参数，注解
         * https://blog.csdn.net/u013259361/article/details/102913329
         * https://blog.csdn.net/shenyunsese/article/details/51133065
         */

        // 获取注解中的值
        System.out.println("注解中的值 : " + auth.desc());

        try {
            // 检验是否登录 true 已经登录  false 未登录
            Boolean flag = false;
            if (flag){
                return "登录成功";
            }else {
                return "未登录";
            }
        } catch (Exception e) {
            return null;
        }

    }
}