package com.example.springboot.JWT.repository;

import com.example.springboot.JWT.entity.JwtRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: Spring
 * @description: zzz
 * @author: Mr.Wang
 * @create: 2020-04-16 14:07
 **/
public interface JwtRoleRepository extends JpaRepository<JwtRole,Integer> {
    JwtRole findByPermission(String permission);
}
