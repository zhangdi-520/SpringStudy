package com.example.springboot.shiro.repository;

import com.example.springboot.shiro.entity.role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version V1.0
 * @program: Spring
 * @description: 角色JPA工厂
 * @author: Mr.Zhang
 * @create: 2020-04-15 14:46
 **/
public interface RoleRepository extends JpaRepository<role,Integer> {
}
