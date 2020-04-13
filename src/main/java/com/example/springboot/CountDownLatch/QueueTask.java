package com.example.springboot.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @version V1.0
 * @program: Spring
 * @description: 排队任务
 * @author: Mr.Zhang
 * @create: 2020-04-13 11:03
 **/
public class QueueTask implements Runnable{

    private CountDownLatch countDownLatch;

    public QueueTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
            System.out.println("排队成功，可以开始交易");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(countDownLatch!=null){
                countDownLatch.countDown();
            }
        }
    }
}
