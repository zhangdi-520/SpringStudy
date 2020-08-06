package com.example.springboot.ORM.xhyl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @program: Spring
 * @description: 研究循环依赖
 * @author: Mr.Zhang
 * @create: 2020-07-31 14:51
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StudentA {

    private StudentB studentB;
}
