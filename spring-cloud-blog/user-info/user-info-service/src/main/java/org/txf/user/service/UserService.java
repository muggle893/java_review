package org.txf.user.service;


import org.txf.user.api.pojo.UserInfoRequest;
import org.txf.user.api.pojo.UserInfoResponse;
import org.txf.user.api.pojo.UserLoginResponse;

public interface UserService {
    UserLoginResponse login(UserInfoRequest user);

    UserInfoResponse getUserInfo(Integer userId);

    UserInfoResponse selectAuthorInfoByBlogId(Integer blogId);
}
