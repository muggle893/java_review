package org.txf.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/config")
public class NacosController {
    @Value("${nacos.test.num:0}")
    private Integer num;
    @RequestMapping("/get")
    public Integer getNum() {
        return num;
    }
}
