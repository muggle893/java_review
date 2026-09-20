package org.txf.springaopdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.springaopdemo.aop.CostTime;
import org.txf.springaopdemo.aop.MyAspect;

@RestController
@RequestMapping("/test")
public class TestController {
    // 在连接点上加上@MyAspect表示这个连接点作为一个切点
    @MyAspect
    @CostTime
    @RequestMapping("/t1")
    public void test() {
    }

    @MyAspect
    @CostTime
    @RequestMapping("/t2")
    public void test2() {
        System.out.println(10 / 0);
    }
}
