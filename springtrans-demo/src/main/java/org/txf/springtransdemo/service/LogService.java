package org.txf.springtransdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.txf.springtransdemo.dao.LogInfoMapper;

@Slf4j
@Service
public class LogService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertLog(String name,String op){
        // 在这里抛出异常测试事务传播机制
        try {
            logInfoMapper.insertLog(name, op);
            System.out.println(10 / 0);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void insertLog5(String name,String op){
        // 在这里抛出异常测试事务传播机制
        try {
            logInfoMapper.insertLog(name, op);
            System.out.println(10 / 0);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void insertLog6(String name,String op){
        // 在这里抛出异常测试事务传播机制
        try {
            logInfoMapper.insertLog(name, op);
            System.out.println(10 / 0);
        } catch (Exception e) {
            throw e;
        }
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertLog7(String name,String op){
        // 在这里抛出异常测试事务传播机制
        try {
            logInfoMapper.insertLog(name, op);
            System.out.println(10 / 0);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void insertLog8(String name,String op){
        // 在这里抛出异常测试事务传播机制
        logInfoMapper.insertLog(name, op);
        try {
            System.out.println(10 / 0);
        } catch (Exception e) {
            throw e;
        }
    }
}
