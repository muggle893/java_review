package com.bite.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/consul")
@RestController
@RefreshScope
public class ConsulController {
    @Value("${service-name}")
    private String serviceName;
    @Value("${output.info}")
    private String outputInfo;

    @RequestMapping("/getConfigByConsul")
    public String getConfigByConsul(){
        return String.format("从consul获取配置, serviceName:%s, outputInfo:%s",
                serviceName, outputInfo);
    }
}
