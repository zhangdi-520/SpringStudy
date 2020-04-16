package com.example.springboot.JWT.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @version V1.0
 * @program: Spring
 * @description: token
 * @author: Mr.Zhang
 * @create: 2020-04-16 10:47
 **/
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
