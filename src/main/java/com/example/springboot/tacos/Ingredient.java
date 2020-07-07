package com.example.springboot.tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;



/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-05-15 14:39
 **/
@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type{
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
