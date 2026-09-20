package org.txf.springaopdemo.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {
    // 被代理目标对象
    private Object target;
    public JDKInvocationHandler(Object target) {
        this.target = target;
    }
    // 实现代理方法，让代理处理一些别的事情

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理");
        Object res = method.invoke(target, args);
        System.out.println("代理结束");
        return res;
    }
}
