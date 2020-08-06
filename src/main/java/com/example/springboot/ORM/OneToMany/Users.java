package com.example.springboot.ORM.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-08-03 10:44
 **/
@Entity
@Table(name="jpa_users")
@Data
@NoArgsConstructor
@DynamicUpdate(value = true)
@DynamicInsert
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private Integer roleid;

    //cascadeb保存用户是级联保存角色
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    //用来维护外键
//    @JoinColumn(name = "roles_id")
//    private Roles roles;
}
