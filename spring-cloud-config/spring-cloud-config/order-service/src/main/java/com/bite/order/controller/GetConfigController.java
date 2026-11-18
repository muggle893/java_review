package com.bite.order.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/config")
public class GetConfigController {
    @Value("${data.env}")
    private String env;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;

    @RequestMapping("/env")
    public String getEnv() {
        return env;
    }

    @RequestMapping("/mysql")
    public String getMysqlInfo() {
        return username + "\n" + password;
    }
}
