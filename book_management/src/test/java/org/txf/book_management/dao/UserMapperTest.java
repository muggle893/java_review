package org.txf.book_management.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void getUserByUsnAndPwd() {
        // 测试用户名和密码查询用户
        userMapper.getUserByUsnAndPwd("admin", "admin");
    }
}