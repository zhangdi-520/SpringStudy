package com.example.springboot.shiro.exception;

import com.example.springboot.shiro.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountException;

/**
 * @version V1.0
 * @program: Spring
 * @description: 捕获异常类
 * @author: Mr.Zhang
 * @create: 2020-04-15 16:09
 **/
//@RestControllerAdvice
//public class ExceptionController {
//
//    private final ResultMap resultMap;
//
//    @Autowired
//    public ExceptionController(ResultMap resultMap) {
//        this.resultMap = resultMap;
//    }
//
//    @ExceptionHandler(AccountException.class)
//    public ResultMap handleShiroException(Exception ex){
//        return resultMap.fail().message(ex.getMessage());
//    }
//}
