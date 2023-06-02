package com.qstar.demo.filter;

import com.qstar.demo.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;


public class TokenInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  //这个技术我不是很确认，没测试过
        String token=request.getHeader("token");
        Map<String, User> map=(Map<String, User>)request.getServletContext().getAttribute("tokens");
        return map.containsKey(token);      //是否在link的map中包含这个token，包含则代表已登录，等cjd的接口方法checkToken
    }
}
