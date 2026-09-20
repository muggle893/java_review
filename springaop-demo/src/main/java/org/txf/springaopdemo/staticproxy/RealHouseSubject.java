package org.txf.springaopdemo.staticproxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealHouseSubject implements HouseSubject {
    @Override
    public void rentHouse() {
      log.info("房东出售房子.");
    }
}
