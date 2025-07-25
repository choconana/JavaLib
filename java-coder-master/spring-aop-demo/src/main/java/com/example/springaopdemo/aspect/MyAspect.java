package com.example.springaopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyAspect.java
 * @Description TODO
 * @createTime 2022年05月02日 20:32:00
 */
@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(public * com.example.springaopdemo.*.TargetClass.test(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void onBefore(JoinPoint joinPoint) {
        System.out.println("onBefore：" + joinPoint.getSignature().getName() + "方法开始执行，参数："
                + Arrays.asList(joinPoint.getArgs()));
    }

    @After("pointcut()")
    public void onAfter(JoinPoint joinPoint) {
        System.out.println("onAfter：" + joinPoint.getSignature().getName() + "方法执行结束，参数："
                + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("afterReturning：" + joinPoint.getSignature().getName() + "方法执行结束返回，参数："
                + Arrays.asList(joinPoint.getArgs()) + "，返回值：" + result);
    }

    @AfterThrowing(value = "pointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) {
        System.out.println("afterThrowing：" + joinPoint.getSignature().getName() + "方法执行出错，参数："
                + Arrays.asList(joinPoint.getArgs()) + "，异常：" + exception);
    }

}