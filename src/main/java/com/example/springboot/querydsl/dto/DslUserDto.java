package com.example.springboot.querydsl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-17 15:29
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DslUserDto {

    private String userId;

    private String username;

    private String nickname;

    private String birthday;
}
