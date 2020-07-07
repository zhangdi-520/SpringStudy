package com.example.springboot.querydsl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tb_brand")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String letter;

    @ManyToMany
    private List<Category> categoryList;
}