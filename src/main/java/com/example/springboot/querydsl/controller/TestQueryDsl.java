package com.example.springboot.querydsl.controller;

import com.example.springboot.querydsl.dto.DslUserDto;
import com.example.springboot.querydsl.entity.DslUser;
import com.example.springboot.querydsl.service.DslUserService;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-17 15:37
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQueryDsl {

    private static final String NULL_RESULT="NO RESULT";

    @Autowired
    DslUserService dslUserService;

    @Test
    public void test(){
        dslUserService.addTask();
        //dslUserService.test();
        //System.out.println(dslUserService.findByUsernameAndPassword("zhangsan","123456").toString());
        //System.out.println(dslUserService.findAll());
//        Date start = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(start);
//        calendar.add(Calendar.DAY_OF_MONTH,-1);
//        start = calendar.getTime();
//        Date end = new Date();
//        System.out.println(dslUserService.findByBirthdayBetween(start,end));
//        NewPageable newPageable = new NewPageable(2,0);
//        List<DslUserDto> dslUserDtoList  = dslUserService.findAllUserDto(newPageable);
//        System.out.println(dslUserDtoList);
//        System.out.println(dslUserService.findByNicknameAndUsername("aaa","zhangsan"));
//        System.out.println(dslUserService.countByUserNameLike("i"));
//        Page<DslUser> page = dslUserService.findByUserProperties("lisi",null,null,null,null);
//        System.out.println(page.getContent());
//        System.out.println(dslUserService.findByDepatmentIdDTO(1));
//        System.out.println(dslUserService.findByDepatmentIdDTO(1));
//          dslUserService.test();
//        DslUserDto dslUserDto = new DslUserDto();
//        System.out.println(dslUserDto.toString());
//        Field[] fields = dslUserDto.getClass().getDeclaredFields();
//        for(Field f:fields){
//            f.setAccessible(true);
//            try {
//                if(f.getType()==String.class&&f.get(dslUserDto)==null){
//                    f.set(dslUserDto,"111");
//                }
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(dslUserDto.toString());
//        dslUserService.initDslUserDto(dslUserDto);
//        System.out.println(dslUserDto.toString());
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        System.out.println(stopwatch);
//        AtomicInteger s = new AtomicInteger(0);
//        System.out.println(s);
//        System.out.println(LocalDate.now());
//        System.out.println(Optional.of(NULL_RESULT));



    }
}
