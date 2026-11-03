package org.txf.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.txf.blog.api.BlogServiceApi;
import org.txf.blog.api.pojo.BlogInfoResponse;
import org.txf.common.exception.BlogException;
import org.txf.common.pojo.Result;
import org.txf.common.utils.JWTUtils;
import org.txf.common.utils.SecurityUtil;
import org.txf.user.api.pojo.UserInfoRequest;
import org.txf.user.api.pojo.UserInfoResponse;
import org.txf.user.api.pojo.UserLoginResponse;
import org.txf.user.common.pojo.dataobject.UserInfo;
import org.txf.user.mapper.UserInfoMapper;
import org.txf.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BlogServiceApi blogServiceApi;

    @Override
    public UserLoginResponse login(UserInfoRequest user) {
        //验证账号密码是否正确
        UserInfo userInfo = selectUserInfoByName(user.getUserName());
        if (userInfo==null || userInfo.getId()==null){
            throw new BlogException("用户不存在");
        }
//        if (!user.getPassword().equals(userInfo.getPassword())){
//            throw new BlogException("用户密码不正确");
//        }
        if (!SecurityUtil.verify(user.getPassword(),userInfo.getPassword())){
            throw new BlogException("用户密码不正确");
        }
        //账号密码正确的逻辑
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userInfo.getId());
        claims.put("name", userInfo.getUserName());

        String jwt = JWTUtils.genJwt(claims);
        return new UserLoginResponse(userInfo.getId(), jwt);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        UserInfo userInfo = selectUserInfoById(userId);
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse selectAuthorInfoByBlogId(Integer blogId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        //1. 根据博客ID, 获取作者ID
        // 这里要远程调用博客服务
        Result<BlogInfoResponse> result = blogServiceApi.getBlogDeatail(blogId);

        BlogInfoResponse blogInfo = result.getData();
        //2. 根据作者ID, 获取作者信息
        if (blogInfo == null){
            throw new BlogException("博客不存在");
        }
        UserInfo userInfo = selectUserInfoById(blogInfo.getUserId());
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    public UserInfo selectUserInfoByName(String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName, userName).eq(UserInfo::getDeleteFlag, 0));
    }
    private UserInfo selectUserInfoById(Integer userId) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, userId).eq(UserInfo::getDeleteFlag, 0));
    }

}
