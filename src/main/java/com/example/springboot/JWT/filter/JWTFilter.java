package com.example.springboot.JWT.filter;

import com.auth0.jwt.JWT;
import com.example.springboot.JWT.shiro.JWTToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.reactive.TransactionalOperatorExtensionsKt;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @version V1.0
 * @program: Spring
 * @description: JWT过滤器
 * @author: Mr.Zhang
 * @create: 2020-04-16 10:35
 **/
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @Description: 如果带有token，则对token进行检查，否则直接通过
     * @Param: [request, response, mappedValue]
     * @return: boolean
     * @Author: Mr.Wang
     * @Date: 2020/4/16
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(isLoginAttempt(request,response)){
            try{
                executeLogin(request,response);
                return true;
            }catch (Exception e){
                responseError(response,e.getMessage());
            }
        }
        return true;
    }

    /**
     * @Description: 判断用户是否想要登入，检测header里面是否包含Token字段
     * @Param: [request, response]
     * @return: boolean
     * @Author: Mr.Wang
     * @Date: 2020/4/16
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest)request;
        String token = req.getHeader("Token");
        return token!=null;
    }

    /**
     * @Description: 执行登录操作
     * @Param: [request, response]
     * @return: boolean
     * @Author: Mr.Wang
     * @Date: 2020/4/16
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JWTToken jwtToken = new JWTToken(token);
        getSubject(request,response).login(jwtToken);
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * @Description: 将非法请求跳转到/unauthorized/**
     * @Param: [response, message]
     * @return: void
     * @Author: Mr.Wang
     * @Date: 2020/4/16
     */
    private void responseError(ServletResponse response,String message){
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        try {
            message = URLEncoder.encode(message,"UTF-8");
            httpServletResponse.sendRedirect("/unauthorized/"+message);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
