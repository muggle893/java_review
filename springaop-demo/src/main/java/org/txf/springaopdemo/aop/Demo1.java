package org.txf.springaopdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 这个类用来测试几种通知方法
 * @Around:环绕通知,此注解标注的通知⽅法在⽬标⽅法前,后都被执⾏
 * •
 * @Before:前置通知,此注解标注的通知⽅法在⽬标⽅法前被执⾏
 * •
 * @After:后置通知,此注解标注的通知⽅法在⽬标⽅法后被执⾏,⽆论是否有异常都会执⾏
 * •
 * @AfterReturning: 返回后通知, 此注解标注的通知⽅法在⽬标⽅法后被执⾏,有异常不会执⾏
 * •
 * @AfterThrowing:异常后通知,此注解标注的通知⽅法发⽣异常后执⾏
 */

@Slf4j
@Aspect
@Component
@Order(1)
public class Demo1 {

    // 用@pointCut注解提取公共切点表达式
    @Pointcut("execution(* org.txf.springaopdemo.controller.*.*(..))")
    public void pt() {
    }

    // after和before的方法的方法参数不能接收ProccedingJoinPoint类型的参数
    // 使用ProccedingJoinPoint类型接收，是接收不了的
    @Before("pt()")
    public void testAround(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toString() + "方法前执行.Before");
    }

    @After("pt()")
    public void testAfter(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toString() + "方法前执行.After");
    }


    // 发生异常的时候不会执行afterReturning
    @AfterReturning("pt()")
    public void testAfterReturning(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toString() + "方法前执行.AfterReturning");
    }

    @AfterThrowing("pt()")
    public void testAfterThrowing(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().toString() + "方法前执行.AfterThrowing");
    }

    // 使用@annotation表示式来指定切点
    @Around("@annotation(org.txf.springaopdemo.aop.CostTime)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info(joinPoint.getSignature().getName() + "方法耗时：" + (endTime - startTime) + "ms");
        return result;
    }
}
