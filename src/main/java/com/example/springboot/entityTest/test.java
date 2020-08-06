package com.example.springboot.entityTest;


import com.example.springboot.querydsl.repository.BrandRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-21 16:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private Gjj51Repository gjj51Repository;

    @Autowired
    private Gjj51DetailRepository gjj51DetailRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void testSave(){
//        Gjj51Data gjj51Data = new Gjj51Data();
        RowKeyPK rowKeyPK = new RowKeyPK("320721199512200159",System.currentTimeMillis());
//        gjj51Data.setRowKeyPK(rowKeyPK);
//        gjj51Data.setUser(new User("张无忌1","1631","zzz1"));
        Gjj51DataResult gjj51DataResult = new Gjj51DataResult();
        gjj51DataResult.setAmount(123);
        gjj51DataResult.setRowKeyPK(rowKeyPK);
        Gjj51DataResult gjj51DataResult1 = new Gjj51DataResult();
        gjj51DataResult1.setAmount(4563);
        gjj51DataResult1.setRowKeyPK(rowKeyPK);
        Set<Gjj51DataResult> gjj51DataResults = new HashSet<>();
        gjj51DataResults.add(gjj51DataResult);
        gjj51DataResults.add(gjj51DataResult1);
//        gjj51Data.setGjj51DataResults(gjj51DataResults);
//        gjj51Repository.save(gjj51Data);
//        gjj51DetailRepository.saveAll(gjj51DataResults.stream().collect(Collectors.toList()));

//        System.out.println(LocalDateTime.now().minusDays(30).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        gjj51DataResults.stream().filter(timestamp(Gjj51DataResult->Long.valueOf(Gjj51DataResult.getIdCard()))).collect(Collectors.toList());
        long i = gjj51DataResult.getAmount();

    }


    @Test
    public void testfind(){
//        Optional<Gjj51Data> gjj51Data = gjj51Repository.findByIdCard("320721199512200159");
//        if(gjj51Data.isPresent()){
//            System.out.println(gjj51Data.get().toString());
//        }else{
//            System.out.println("未查询到用户信息");
//        }
//        RowKeyPK rowKeyPK = new RowKeyPK("320721199512200159",1595389166151l);
//        Optional<Gjj51Data> byId = gjj51Repository.findById(rowKeyPK);
//        if(byId.isPresent()){
//            System.out.println(byId.get().toString());
//        }else{
//            System.out.println("为查询到该用户信息");
//        }

        Optional<Gjj51DataVo> byIdenty = gjj51Repository.findByIdenty("320721199512200159");
        if(byIdenty.isPresent()){
            System.out.println(byIdenty.get().toString());
        }else{
            System.out.println("未查询到用户信息");
        }


    }


    public static  <T> Predicate<T>  timestamp(ToLongFunction<T> longFunction){
        System.out.println("执行了");
        return t->{
            System.out.println(t);
            System.out.println("匿名内部类执行了");
            long l = longFunction.applyAsLong(t);
            return true;
        };

    }

    /*
    * TODO 研究jpa用法及事务的传递性
    *  下午目标
    * */

    @Test
    public void testRedis(){


    }


}
