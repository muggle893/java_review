package org.txf.user.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.txf.common.pojo.Result;
import org.txf.user.api.pojo.UserInfoRegisterRequest;
import org.txf.user.api.pojo.UserInfoRequest;
import org.txf.user.api.pojo.UserInfoResponse;
import org.txf.user.api.pojo.UserLoginResponse;


/**
 * 提供用户服务的远程调用接口
 */
@FeignClient(value = "user-service", path = "/user")
public interface UserServiceApi {
    @RequestMapping("/register")
    Result<Integer> register(@RequestBody UserInfoRegisterRequest userInfoRegisterRequest);

    @RequestMapping("/login")
    UserLoginResponse login(@RequestBody UserInfoRequest user);


    @RequestMapping("/getUserInfo")
    UserInfoResponse getUserInfo(@RequestParam("userId") Integer userId);


    @RequestMapping("/getAuthorInfo")
    UserInfoResponse getAuthorInfo(@RequestParam("blogId") Integer blogId);
}
