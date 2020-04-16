package com.example.springboot.JWT.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @version V1.0
 * @program: Spring
 * @description: JWT工具类
 * @author: Mr.Zhang
 * @create: 2020-04-16 09:37
 **/
public class JWTUtil {

    private static final long EXPIRE_TIME = 60*24*60*1000;

    private static final String SECRET = "SHIRO+JWT";

    /**
     * @Description: 生成token
     * @Param: [username]
     * @return: java.lang.String
     * @Author: Mr.Wang
     * @Date: 2020/4/16
     */
    public static String createToken(String username){
        try {
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("username",username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @Description: 检验token是否正确
     * @Param: [token, username]
     * @return: boolean
     * @Author: Mr.Wang
     * @Date: 2020/4/16
     */
    public static boolean verify(String token,String username){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username",username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    /**
     * @Description: 获得token中信息
     * @Param: [token]
     * @return: java.lang.String
     * @Author: Mr.Wang
     * @Date: 2020/4/16
     */
    public static String getUsername(String token){
        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }

    }
}
