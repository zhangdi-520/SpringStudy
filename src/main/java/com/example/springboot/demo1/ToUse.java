package com.example.springboot.demo1;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class ToUse {


    public void use(){
        Car car = new Car();
        car.carName();
    }
}
