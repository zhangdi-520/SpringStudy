package com.example.springboot.querydsl.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent; // 注意isParent生成的getter和setter方法需要手动加上Is
    private Integer sort;
    // getter和setter略

    @ManyToMany(mappedBy = "categoryList")
    private List<Brand> brandList;
}
