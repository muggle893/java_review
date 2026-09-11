package org.txf.book_management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.book_management.model.User;
import org.txf.book_management.service.UserService;

// @RestController等于@Controller+@ResponseBody
// ResponseBody会把返回的数据写回响应体中而不是把返回值作为一个视图
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public User login(String username, String password) {
        // 1.校验参数(用户名或者密码不对直接返回一个Null对象）
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return null;
        }
        // 2.调用service层的接口登录用户
        User user = userService.login(username, password);
        System.out.println(user);
        return user;
        // 3.将用户信息存到session中，保存登录信息
        // 4.返回数据
    }
}
