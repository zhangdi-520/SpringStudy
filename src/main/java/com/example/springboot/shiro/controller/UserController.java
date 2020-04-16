package com.example.springboot.shiro.controller;

import com.example.springboot.shiro.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @program: Spring
 * @description: 普通用户接口
 * @author: Mr.Zhang
 * @create: 2020-04-15 16:02
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private final ResultMap resultMap;

    @Autowired
    public UserController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @RequestMapping(value = "/getMessage",method = RequestMethod.GET)
    public ResultMap getMessage(){
        return resultMap.success().message("您拥有用户权限，可以获得该接口信息");
    }
}
