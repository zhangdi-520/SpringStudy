package com.example.springboot.querydsl.repository;

import com.example.springboot.querydsl.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @program: Spring
 * @description: 工厂类
 * @author: Mr.Wang
 * @create: 2020-07-03 11:30
 **/
public interface BrandRepository extends JpaRepository<Brand,Long> {
}
