package com.example.springboot.querydsl.repository;

import com.example.springboot.querydsl.entity.DslUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * @program: Spring
 * @description: JPA工厂
 * @author: Mr.Wang
 * @create: 2020-04-17 15:26
 **/
public interface DslUserRepository extends JpaRepository<DslUser,Integer>, QuerydslPredicateExecutor<DslUser> {

}
