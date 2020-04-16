package com.example.springboot.shiro.repository;

import com.example.springboot.shiro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: Spring
 * @description: 用户JPA工厂
 * @author: Mr.Wang
 * @create: 2020-04-15 14:46
 **/
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);
}
