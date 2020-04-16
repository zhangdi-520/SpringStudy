package com.example.springboot.JWT.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-16 10:16
 **/
@Entity
@Table(name = "JwtRole")
@Data
public class JwtRole {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String role;

    private String permission;
}
