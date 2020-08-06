package com.example.springboot.ORM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-27 09:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeOnlyDto implements Serializable {

    private String name;

    private String sex;
}
