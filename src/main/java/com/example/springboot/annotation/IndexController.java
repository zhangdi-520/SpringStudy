package com.example.springboot.annotation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-13 15:43
 **/
@RestController
public class IndexController {

    @GetMapping("/sourceA")
    public String sourceA(){
        return "你正在访问sourceA资源";
    }

    @LoginRequired
    @GetMapping("/sourceB")
    public String sourceB(){
        return "你正在访问sourceB资源";
    }

    @MyLog
    @GetMapping("/sourceC/{source_name}")
    public String sourceC(@PathVariable("source_name")String sourceName){
        return "你正在访问sourceC资源";
    }
}
