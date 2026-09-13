package org.txf.book_management.intercepteor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.txf.book_management.constants.Constants;
import org.txf.book_management.model.User;
import org.txf.book_management.result.RequestResult;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这里判断用户是否登录
        log.info("登录拦截器执行，判断用户是否登录.");
        User user = (User)request.getSession().getAttribute(Constants.USER_SESSION_KEY);
        if (user == null) {
            log.info("用户未登录.");
            //用户未登录
            response.setStatus(401);
            response.setContentType("application/json;charset=utf-8");
            RequestResult<Object> requestResult = RequestResult.fail("用户未登录");
            response.getOutputStream().write(objectMapper.writeValueAsString(requestResult).getBytes());
            return false;
        }
        log.info("用户登录校验通过.");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
