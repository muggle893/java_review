package org.txf.springtransdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.springtransdemo.service.LogService;
import org.txf.springtransdemo.service.UserService;

import java.io.IOException;

@RequestMapping("/trans")
@RestController
@Slf4j
public class TransactionalController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    @Transactional
    @RequestMapping("/registry")
    public String registry(String name,String password){
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/r2")
    public String r2(String name,String password) throws IOException {
        userService.registryUser(name, password);
        log.info("⽤⼾数据插⼊成功 ");
        if (true) {
            throw new IOException();
        }
        return "r2";
    }

    /** 测试事务传播机制的Required机制
     * @param name
     * @param password
     * @return
     */
    @Transactional
    @RequestMapping("/r3")
    public String r3(String name,String password) {
        userService.registryUser(name, password);
        logService.insertLog(name, "用户注册");
        return "r3";
    }

    /** 测试事务传播机制的Requires-new机制
     * @param name
     * @param password
     * @return
     */
    @Transactional
    @RequestMapping("/r4")
    public String r4(String name,String password) {
        userService.registryUser(name, password);
        logService.insertLog(name, "用户注册");
        return "r4";
    }

    /**
     * 测试Supports类型传播机制
     * 在有事务的时候会创建事务但是，没有事务的时候也不会创建事务
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/r5")
    public String r5(String name,String password) {
        userService.registryUser5(name, password);
        logService.insertLog5(name, "用户注册");
        return "r5";
    }

    /**
     * 测试Mandatory类型传播机制
     * 当前不存在事务的时候则抛出异常
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/r6")
    public String r6(String name,String password) {
        userService.registryUser6(name, password);
        logService.insertLog6(name, "用户注册");
        return "r6";
    }

    /**
     * 测试No-Support类型传播机制
     * 不管当前有没有事务，都以非事务的方式运行
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/r7")
    public String r7(String name,String password) {
        userService.registryUser7(name, password);
        logService.insertLog7(name, "用户注册");
        return "r7";
    }

    /**
     * 测试Nested类型传播机制
     * 如果当前有事务则创建子事务，没有事务的时候等价于PROPAGATION_REQUIRED
     * 子事务发生异常导致回滚，父事务也会回滚
     * @param name
     * @param password
     * @return
     */
    @RequestMapping("/r8")
    @Transactional
    public String r8(String name,String password) {
        userService.registryUser8(name, password);
        logService.insertLog8(name, "用户注册");
        return "r8";
    }
}
