package com.example.domain.repository;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: gxy
 * @CreateDate: 2019/12/25 13:32
 * @Description:学习自定义注解和AOP
 * https://mp.weixin.qq.com/s?__biz=MzI3NzE0NjcwMg==&mid=2650122353&idx=2&sn=80c8e0c405daef92ca358033b9eab7f1&chksm=f36bb550c41c3c468a66405fae570c9b843b2887bcd59e20dbe124bc091033187bad97e2ea60&mpshare=1&scene=1&srcid=1025bMJUiKL722VKkxHDZlQL#rd
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 功能描述: 拦截对这个包下所有方法的访问
     *
     * @param:[]
     * @return:void
     * Spring AOP 中@Pointcut的用法
     * https://www.cnblogs.com/liaojie970/p/7883687.html
     **/
    @Pointcut("execution(* com.example.*Controller.*(..))")
    public void loginLog(){}

    // 前置通知
    @Before("loginLog()")
    public void loginBefore(JoinPoint joinPoint){
        // 我们从请求的上下文中获取request，记录请求的内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        System.out.println("请求路径 : " + request.getRequestURL());
        System.out.println("请求方式 : " + request.getMethod());
        System.out.println("方法名 : " + joinPoint.getSignature().getName());
        System.out.println("类路径 : " + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    //后置增强，方法退出时执行
    @AfterReturning(returning = "object", pointcut = "loginLog()")
    public void doAfterReturning(Object object){
        System.out.println("方法的返回值 : " + object);
    }

    // 方法发生异常时执行该方法
    @AfterThrowing(throwing = "e",pointcut = "loginLog()")
    public void throwsExecute(JoinPoint joinPoint, Exception e) {

        System.err.println("方法执行异常 : " + e.getMessage());
    }

    // 后置通知 最终增强，无论什么情况都会执行
    @After("loginLog()")
    public void afterInform() {

        System.out.println("后置通知结束");
    }

    @Around("loginLog()")
    public Object surroundInform(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知开始...");

        try {
            Object o = proceedingJoinPoint.proceed();//调用下一个切面或目标方法
            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

    }
}