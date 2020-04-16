package com.example.springboot.shiro.controller;

import com.example.springboot.shiro.entity.User;
import com.example.springboot.shiro.model.ResultMap;
import com.example.springboot.shiro.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @program: Spring
 * @description: 登录接口
 * @author: Mr.Zhang
 * @create: 2020-04-15 16:18
 **/
@RestController
public class LoginController {

    @Autowired
    private ResultMap resultMap;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/notLogin",method = RequestMethod.GET)
    public ResultMap notLogin(){
        return resultMap.success().message("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole",method = RequestMethod.GET)
    public ResultMap notRole(){
        return resultMap.success().message("您没有权限！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResultMap logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return resultMap.success().message("成功注销！");
    }

    @PostMapping(value = "/login")
    public ResultMap login(String username, String password) {
        System.out.println("yes");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);

        User user = userRepository.findByUsername(username);
        String role = null;
        if(user!=null){
            role = user.getRole();
        }
        if ("user".equals(role)) {
            return resultMap.success().message("欢迎登陆");
        }
        if ("admin".equals(role)) {
            return resultMap.success().message("欢迎来到管理员页面");
        }
        return resultMap.fail().message("权限错误！");
    }


}
