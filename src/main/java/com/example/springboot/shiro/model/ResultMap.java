package com.example.springboot.shiro.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @version V1.0
 * @program: Spring
 * @description: 结果类
 * @author: Mr.Zhang
 * @create: 2020-04-15 15:37
 **/
@Component
public class ResultMap extends HashMap<String, Object> {
    public ResultMap() {
    }

    public ResultMap success() {
        this.put("result", "success");
        return this;
    }

    public ResultMap fail() {
        this.put("result", "fail");
        return this;
    }

    public ResultMap code(int code) {
        this.put("code", code);
        return this;
    }

    public ResultMap message(Object message) {
        this.put("message", message);
        return this;
    }
}