package com.example.springboot.Socket;


import org.springframework.stereotype.Service;


/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-05-11 09:31
 **/
@Service
public class RuleExecutor implements Executor {


    @Override
    public void get() {
        System.out.println("获得到了执行器");
    }
}
