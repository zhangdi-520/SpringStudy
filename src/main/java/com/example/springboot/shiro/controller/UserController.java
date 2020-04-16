package com.example.springboot.shiro.controller;

import com.example.springboot.JWT.repository.JwtUserRepository;
import com.example.springboot.shiro.model.ResultMap;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private JwtUserRepository jwtUserRepository;

    @Autowired
    public UserController(ResultMap resultMap) {
        this.resultMap = resultMap;
    }

//    @RequestMapping(value = "/getMessage",method = RequestMethod.GET)
//    public ResultMap getMessage(){
//        return resultMap.success().message("您拥有用户权限，可以获得该接口信息");
//    }
    /**
     * 拥有 user, admin 角色的用户可以访问下面的页面
     */
    @GetMapping("/getMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap getMessage() {
        return resultMap.success().code(200).message("成功获得信息！");
    }

    @PostMapping("/updatePassword")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    public ResultMap updatePassword(String username, String oldPassword, String newPassword) {
        String dataBasePassword = jwtUserRepository.findByUsername(username).getPassword();
        if (dataBasePassword.equals(oldPassword)) {
            jwtUserRepository.updatePassword(newPassword,username);
        } else {
            return resultMap.fail().message("密码错误！");
        }
        return resultMap.success().code(200).message("成功获得信息！");
    }

    /**
     * 拥有 vip 权限可以访问该页面
     */
    @GetMapping("/getVipMessage")
    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
    @RequiresPermissions("vip")
    public ResultMap getVipMessage() {
        return resultMap.success().code(200).message("成功获得 vip 信息！");
    }
}

