package com.example.springboot.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @program: Spring
 * @description: CountDownLatch案例,线程等待
 * @author: Mr.Zhang
 * @create: 2020-04-13 10:43
 **/
public class SeeDoctorTask implements Runnable {

    private CountDownLatch countDownLatch;

    public SeeDoctorTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(3000);
            System.out.println("看大夫成功，答复给开了些药单子");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(countDownLatch!=null){
                countDownLatch.countDown();
            }
        }
    }
}
