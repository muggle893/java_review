package org.txf.springaopdemo.dynamicproxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import org.txf.springaopdemo.staticproxy.HouseSubject;
import org.txf.springaopdemo.staticproxy.RealHouseSubject;

public class Test {
    public static void main(String[] args) {
        // 先创建被代理对象
        RealHouseSubject target = new RealHouseSubject();
        HouseSubject proxy = (HouseSubject) Enhancer.create(target.getClass(), new MyMethodInterceptor(target));
        proxy.rentHouse();
    }
}
