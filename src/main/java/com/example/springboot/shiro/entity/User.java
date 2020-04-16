package com.example.springboot.shiro.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: User表
 * @author: Mr.Zhang
 * @create: 2020-04-15 14:01
 **/
@Entity
@Table(name = "User")
@Data
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String role;
}
