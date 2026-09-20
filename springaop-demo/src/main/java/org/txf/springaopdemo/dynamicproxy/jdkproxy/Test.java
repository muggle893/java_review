package org.txf.springaopdemo.dynamicproxy.jdkproxy;

import org.txf.springaopdemo.staticproxy.HouseSubject;
import org.txf.springaopdemo.staticproxy.RealHouseSubject;

import java.lang.reflect.Proxy;

public class Test {
    /**
     * 测试jdk的动态代理
     * @param args
     */
    public static void main(String[] args) {
        HouseSubject target=  new RealHouseSubject();
        //创建⼀个代理类：通过被代理类、被代理实现的接⼝、⽅法调⽤处理器来创建

        HouseSubject proxy = (HouseSubject) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[]{HouseSubject.class},
                new JDKInvocationHandler(target)
        );
        proxy.rentHouse();
    }
}
