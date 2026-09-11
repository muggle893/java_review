package org.txf.book_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.txf.book_management.dao.UserMapper;
import org.txf.book_management.model.User;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User login(String username, String password) {
        return userMapper.getUserByUsnAndPwd(username, password);
    }
}
