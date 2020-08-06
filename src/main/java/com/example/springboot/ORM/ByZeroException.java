package com.example.springboot.ORM;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-29 15:05
 **/
public class ByZeroException extends RuntimeException {
    public ByZeroException() {
        super("你是猪吗，一能除0吗");
    }
}
