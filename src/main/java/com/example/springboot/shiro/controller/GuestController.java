package com.example.springboot.shiro.controller;

import com.example.springboot.shiro.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @program: Spring
 * @description: 游客接口
 * @author: Mr.Zhang
 * @create: 2020-04-15 15:34
 **/
@RestController
@RequestMapping("/guest")
public class GuestController {

    private final ResultMap resultMap;

    @Autowired
    public GuestController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @RequestMapping(value = "/enter",method = RequestMethod.GET)
    public ResultMap login(){
        return resultMap.success().message("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage",method = RequestMethod.GET)
    public ResultMap submitLogin(){
        return resultMap.success().message("您拥有获得该接口的信息权限！");
    }
}
