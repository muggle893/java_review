package org.txf.springaopdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Order(3)
public class Demo3 {
    @After("org.txf.springaopdemo.aop.Demo1.pt()")
    public void doAfter() {
        log.info("doAfter");
    }

    @Before("org.txf.springaopdemo.aop.Demo1.pt()")
    public void doBefore() {
        log.info("doBefore");
    }
}
