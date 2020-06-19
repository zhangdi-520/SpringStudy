package com.example.springboot.shiro.controller;

import com.example.springboot.JWT.entity.JwtUser;
import com.example.springboot.JWT.repository.JwtUserRepository;
import com.example.springboot.shiro.model.ResultMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @program: Spring
 * @description: 管理员接口
 * @author: Mr.Zhang
 * @create: 2020-04-15 16:05
 **/
@Api(tags = {"管理员接口"})
@RestController
@RequestMapping("/admin")
@Transactional
public class AdminController {

    private final ResultMap resultMap;

    @Autowired
    public AdminController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

    @Autowired
    private JwtUserRepository jwtUserRepository;

    @ApiOperation(value = "获取管理员接口信息")
    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        System.out.println("yes");
        return resultMap.success().message("您拥有管理员权限，可以获得该接口的信息！");
    }

    @GetMapping("/getUser")
    @RequiresRoles("admin")
    public ResultMap getUser() {
        List<JwtUser> jwtUsers = jwtUserRepository.findAll();
        List<String> list = new ArrayList<>();
        for(JwtUser user:jwtUsers){
        list.add(user.getUsername());
        }
        return resultMap.success().code(200).message(list);
    }

    /**
     * 封号操作
     */
    @PostMapping("/banUser")
    @RequiresRoles("admin")
    public ResultMap updatePassword(String username) {
        System.out.println("yes");
        jwtUserRepository.deleteByUsername(1,username);
        return resultMap.success().code(200).message("成功封号！");
    }
}