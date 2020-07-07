package com.example.springboot.utils.orika;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-07 17:12
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopyUser {

    private String name;

    private String password;
}
