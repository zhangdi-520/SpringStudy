package com.example.springboot.ORM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-27 08:54
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_employee")
public class Employee {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid2")
    private String id;

    private String name;

    private String sex;

    private String height;
}
