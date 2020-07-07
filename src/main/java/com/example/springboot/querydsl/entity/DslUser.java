package com.example.springboot.querydsl.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-17 15:18
 **/
@Data
@Entity
@Table(name = "t_user")
public class DslUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;
    private String nickName;
    private Date birthday;
    private BigDecimal uIndex;

    private Integer departmentId;

    @ManyToMany(mappedBy = "dslUserList")
    private List<Department> departmentList;
}

