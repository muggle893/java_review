package org.txf.springaopdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(2)
public class Demo2 {
    @Before("org.txf.springaopdemo.aop.Demo1.pt()")
    public void doBefore(JoinPoint joinPoint) {
      log.info("Demo2.doBefore");
    }

    @After("org.txf.springaopdemo.aop.Demo1.pt()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("Demo2.doAfter");
    }
}
