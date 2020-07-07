package com.example.springboot.querydsl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-20 11:05
 **/
@Data
@Entity
@Table(name = "t_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;    //部门id
    private String deptName;   //部门名称
    private Date createDate;   //创建时间

    @ManyToMany
    private List<DslUser> dslUserList;

}
