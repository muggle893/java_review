package org.txf.springaopdemo.dynamicproxy.cglibproxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {

    // 被代理对象
    private Object target;

    public MyMethodInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // 在这里编写代理增强逻辑
        System.out.println("开始代理.");
        System.out.println("执行被代理对象的方法...");
        Object res = methodProxy.invoke(target, objects);
        System.out.println("结束代理.");
        return res;
    }
}
