package com.example.springboot.annotation;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version V1.0
 * @program: Spring
 * @description: 拦截器
 * @author: Mr.Zhang
 * @create: 2020-04-13 15:49
 **/
public class SourceAccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器了");

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        LoginRequired loginRequired = handlerMethod.getMethod().getAnnotation(LoginRequired.class);
        if(loginRequired==null) {
            return true;
        }
        response.setContentType("text/plain; charset=utf-8");
        response.getWriter().print("你访问的资源需要登录");
        return false;
    }
}
