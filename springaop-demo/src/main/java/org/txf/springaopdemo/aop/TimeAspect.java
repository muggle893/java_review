package org.txf.springaopdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeAspect {

//    @Around("execution(* org.txf.springaopdemo.controller.*.*(..))")
//    public Object timeRecord(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object result = joinPoint.proceed();
//        long endTime = System.currentTimeMillis();
//        log.info(joinPoint.getSignature().getName() + "方法耗时：" + (endTime - startTime) + "ms");
//        return result;
//    }
}
