package org.txf.springtransdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.springtransdemo.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/registry")
    public String registry(String name,String password){
        userService.registryUser(name,password);
        return " 注册成功 ";
    }
}
