package com.example.springboot.JWT.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: Jwt用户表
 * @author: Mr.Zhang
 * @create: 2020-04-16 10:14
 **/
@Entity
@Table(name="JwtUser")
@Data
public class JwtUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String role;

    private String permission;

    private Integer ban;
}
