package com.example.springboot.shiro;

import com.example.springboot.shiro.entity.User;
import com.example.springboot.shiro.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @version V1.0
 * @program: Spring
 * @description: 自定义身份类
 * @author: Mr.Zhang
 * @create: 2020-04-15 14:43
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("----权限认证----");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = userRepository.findByUsername(username);
        String role = null;
        if(user!=null){
            role = user.getRole();
        }
        Set<String> set = new HashSet<>();
        set.add(role);
        info.setRoles(set);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("----身份认证方法----");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        User user = userRepository.findByUsername(token.getUsername());
        String password = null;
        if(user!=null){
            password = user.getPassword();
        }
        if (password==null){
            throw new AccountException("用户名不存在");
        }else if(!password.equals(new String((char[])token.getCredentials()))){
            throw new AccountException("密码不正确");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(),password,getName());
    }
}
