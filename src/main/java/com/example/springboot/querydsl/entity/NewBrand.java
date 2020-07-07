package com.example.springboot.querydsl.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-03 11:55
 **/

@Entity
@Table(name="NewBrand")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class NewBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String letter;
}
