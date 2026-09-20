package org.txf.springaopdemo.staticproxy;

public class TestStaicProxy {
    public static void main(String[] args) {
        new HouseProxySubject(new RealHouseSubject()).rentHouse();
    }
}
