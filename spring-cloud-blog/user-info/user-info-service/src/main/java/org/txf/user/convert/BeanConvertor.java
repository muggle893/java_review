package org.txf.user.convert;

import org.txf.common.utils.SecurityUtil;
import org.txf.user.api.pojo.UserInfoRegisterRequest;
import org.txf.user.common.pojo.dataobject.UserInfo;

public class BeanConvertor {
    public static UserInfo convertUserInfoEncrypt(UserInfoRegisterRequest request) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(request.getUserName());
         userInfo.setPassword(SecurityUtil.encrypt(request.getPassword()));
         userInfo.setGithubUrl(request.getGithubUrl());
         userInfo.setEmail(request.getEmail());
         return userInfo;
    }
}
