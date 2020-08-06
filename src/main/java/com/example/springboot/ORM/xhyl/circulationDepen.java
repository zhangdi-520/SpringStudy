package com.example.springboot.ORM.xhyl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-31 14:55
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class circulationDepen {

    @Autowired
    StudentA studentA;

    @Autowired
    StudentB studentB;

    @Autowired
    StudentC studentC;

    @Test
    public void test(){
        studentA.setStudentB(new StudentB());
    }
}
