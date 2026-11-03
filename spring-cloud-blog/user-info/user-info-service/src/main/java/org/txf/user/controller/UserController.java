package org.txf.user.controller;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.user.api.UserServiceApi;
import org.txf.user.api.pojo.UserInfoRequest;
import org.txf.user.api.pojo.UserInfoResponse;
import org.txf.user.api.pojo.UserLoginResponse;
import org.txf.user.service.UserService;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController implements UserServiceApi {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping("/login")
    public UserLoginResponse login(@Validated @RequestBody UserInfoRequest user){
        log.info("用户登录, userName: {}", user.getUserName());
        return userService.login(user);
    }

    @Override
    @RequestMapping("/getUserInfo")
    public UserInfoResponse getUserInfo(@NotNull Integer userId){
        return userService.getUserInfo(userId);
    }

    @Override
    @RequestMapping("/getAuthorInfo")
    public UserInfoResponse getAuthorInfo(@NotNull Integer blogId){
        log.info("获取文章作者，文章的id:{}", blogId);
        return userService.selectAuthorInfoByBlogId(blogId);
    }
}
