package org.txf.springaopdemo.staticproxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HouseProxySubject implements HouseSubject {
    private HouseSubject houseSubject;
    public HouseProxySubject(HouseSubject houseSubject) {
        this.houseSubject = houseSubject;
    }
    @Override
    public void rentHouse() {
        log.info("我是代理，代理房子买卖开始.");
        houseSubject.rentHouse();
        log.info("我是代理，代理房子买卖结束.");
    }
}
