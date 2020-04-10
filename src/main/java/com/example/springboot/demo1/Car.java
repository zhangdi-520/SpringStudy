package com.example.springboot.demo1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Data
@Component
public class Car {

    private String name;

    private float prcie;

    public void  carName(){
        System.out.println("我可能是一辆本田");
    }
}
