package com.example.springboot.utils.orika;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-07 17:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String name;

    private String password;

    private String height;
}
