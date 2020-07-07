package com.example.springboot.querydsl.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-03 10:58
 **/
@Entity
@Table(name="MoreBrand")
@Data
public class MoreBrand extends NewBrand{

    private String name;

    private String size;

    private String logo;

    private String seq;
}
