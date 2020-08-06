package com.example.springboot.entityTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-27 11:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gjj51DataVo {

    private String idCard;

    private long timestamp;
}
