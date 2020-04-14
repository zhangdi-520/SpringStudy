package com.example.springboot.annotation;

import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-13 15:25
 **/
public class MyFieldTest {

    @MyField(description = "用户名",length = 12)
    private String username;

    public static void main(String[] args) {
        Class c = MyFieldTest.class;

        for(Field f:c.getDeclaredFields()){
            if(f.isAnnotationPresent(MyField.class)){
                MyField annotation = f.getAnnotation(MyField.class);
                System.out.println(f.getName()+":"+annotation.description()+",长度:"+annotation.length());
            }
        }
    }
}
