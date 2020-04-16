package com.example.springboot.shiro;

import com.example.springboot.JWT.entity.JwtRole;
import com.example.springboot.JWT.repository.JwtRoleRepository;
import com.example.springboot.JWT.repository.JwtUserRepository;
import com.example.springboot.JWT.entity.JwtUser;
import com.example.springboot.JWT.shiro.JWTToken;
import com.example.springboot.JWT.util.JWTUtil;
import com.example.springboot.shiro.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
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

    @Autowired
    private JwtUserRepository jwtUserRepository;

    @Autowired
    private JwtRoleRepository jwtRoleRepository;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("----权限认证----");

        String username = JWTUtil.getUsername(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        JwtUser user = jwtUserRepository.findByUsername(username);
        JwtRole Jwtrole = jwtRoleRepository.findByPermission(user.getRole());
        String role = user.getRole();
        String rolePermission = Jwtrole.getPermission();
        String permission = user.getPermission();

        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        roleSet.add(role);
        permissionSet.add(rolePermission);
        permissionSet.add(permission);
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("----身份认证方法----");
        String token = (String)authenticationToken.getCredentials();
        String username = JWTUtil.getUsername(token);
        JwtUser user = jwtUserRepository.findByUsername(username);
        if(username==null||!JWTUtil.verify(token,username)){
            throw new AuthenticationException("token认证失败");
        }
        String password = null;
        int ban = 0;
        if(user!=null){
            password = user.getPassword();
            ban = user.getBan();
        }
        if (password==null){
            throw new AccountException("用户名不存在");
        }
        if(ban==1){
            throw new AuthenticationException("该用户已经被封号");
        }

        return new SimpleAuthenticationInfo(token,token,"MyRealm");
    }
}
