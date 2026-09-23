package org.txf.springtransdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.txf.springtransdemo.dao.UserInfoMapper;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registryUser(String name,String password){
        userInfoMapper.insert(name,password);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void registryUser5(String name,String password){
        userInfoMapper.insert(name,password);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void registryUser6(String name,String password){
        userInfoMapper.insert(name,password);
    }

    /**
     * 不管有没有事务都以非事务的方式运行
     * @param name
     * @param password
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void registryUser7(String name,String password){
        userInfoMapper.insert(name,password);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void registryUser8(String name,String password){
        userInfoMapper.insert(name,password);
    }
}

