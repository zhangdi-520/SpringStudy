package com.example.springboot.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @version V1.0
 * @program: Spring
 * @description: 多线程测试
 * @author: Mr.Zhang
 * @create: 2020-04-13 11:17
 **/
public class MainClient {

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(new SeeDoctorTask(countDownLatch));
        executor.execute(new QueueTask(countDownLatch));
        countDownLatch.await();
        System.out.println("over,回家 cost:"+(System.currentTimeMillis()-now));
        
    }


}
