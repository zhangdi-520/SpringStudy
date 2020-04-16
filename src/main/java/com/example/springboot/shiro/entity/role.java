package com.example.springboot.shiro.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: 角色表
 * @author: Mr.Zhang
 * @create: 2020-04-15 14:05
 **/
@Entity
@Data
@Table(name = "Role")
public class role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String role;
}
