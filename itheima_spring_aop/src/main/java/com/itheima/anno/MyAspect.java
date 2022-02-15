package com.itheima.anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component("myAspect")
@Aspect //标注当前MyAspect是一个切面
public class MyAspect {

    //定义切点表达式
    @Pointcut("execution(* com.itheima.anno.*.*(..))")
    public void pointcut(){}

    //配置前置增强
    @Before(value = "pointcut()")
    public void before(){
        System.out.println("前置增强....");
    }

    public void afterReturning(){
        System.out.println("后置增强....");
    }

    @Around("execution(* com.itheima.anno.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕前增强....");
        //切点方法
        Object proceed = pjp.proceed();
        System.out.println("环绕后增强....");
        return proceed;
    }

    public void afterThrowing(){
        System.out.println("异常抛出增强....");
    }

    @After("MyAspect.pointcut()")
    public void after(){
        System.out.println("最终增强....");
    }

}
