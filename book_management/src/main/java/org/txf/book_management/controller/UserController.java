package org.txf.book_management.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.txf.book_management.constants.Constants;
import org.txf.book_management.model.User;
import org.txf.book_management.result.RequestResult;
import org.txf.book_management.service.UserService;

// @RestController等于@Controller+@ResponseBody
// ResponseBody会把返回的数据写回响应体中而不是把返回值作为一个视图
@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public RequestResult login(String username, String password, HttpServletRequest request) {
        // 1.校验参数(用户名或者密码不对直接返回一个Null对象）
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return RequestResult.fail("用户名或者密码不存在！！！");
        }

        // 2.调用service层的接口登录用户
        // 这里只用用户名查询，因为密码到时候要加密处理的，所以只用用户名查询用户
        User user = userService.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return RequestResult.fail("用户名或者密码错误！！！");
        }
        user.setPassword(null);

        // 3.获取 Session；不存在就创建。将用户信息存到session中，保存登录信息
        HttpSession session = request.getSession(true);
        // 登录成功后更换会话 ID，防止沿用登录前的会话 ID
        request.changeSessionId();
        // 保存登录用户
        session.setAttribute(Constants.USER_SESSION_KEY, user);
        // 30 分钟没有访问，会话过期，单位是秒
        session.setMaxInactiveInterval(30 * 60);

        // 4.返回数据
        return RequestResult.success("登录成功");
    }
}
