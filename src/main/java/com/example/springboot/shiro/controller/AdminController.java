package com.example.springboot.shiro.controller;

import com.example.springboot.shiro.model.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @program: Spring
 * @description: 管理员接口
 * @author: Mr.Zhang
 * @create: 2020-04-15 16:05
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final ResultMap resultMap;

    @Autowired
    public AdminController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        System.out.println("yes");
        return resultMap.success().message("您拥有管理员权限，可以获得该接口的信息！");
    }
}