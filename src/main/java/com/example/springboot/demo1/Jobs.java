package com.example.springboot.demo1;

import com.example.springboot.context.BatchCode;
import com.example.springboot.context.SpringContext;
import com.example.springboot.context.WfService;
import com.example.springboot.lock.DistributionLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@Component
@Configurable
@EnableScheduling
public class Jobs {

    private final static String DAILY_BATCH_REDIS_KEY = "RISK:BATCH:SCHEDULE:DAILY";

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayJob(){
//        System.out.println("fixedDelay 每隔5秒" + new Date());
//    }

    @Autowired
    DistributionLock distributionLock;

    //@Scheduled(fixedDelay = 5000)
    @Scheduled(cron = "${time.cron}")
    public void fixedDelayJob(){
        String lockID = null;
        try{
            lockID = distributionLock.acquireLockWithTimeout(DAILY_BATCH_REDIS_KEY,1000,10000);
            if (StringUtils.isEmpty(lockID)){
                log.info("===================>跑批任务没有获取锁，不执行");
                return;
            }
            log.info("====================>跑批任务开始执行：");
            dealWfTask(BatchCode.DAILY);
        }finally {
            distributionLock.releaseLock(DAILY_BATCH_REDIS_KEY,lockID);
            //System.out.println("暂时不释放锁" );
        }
    }

    public void  useDealWfTask(){
        dealWfTask(BatchCode.DAILY);
    }

    private void dealWfTask(BatchCode batchCode){
        WfService wfService = (WfService) SpringContext.getBean(batchCode.getId().toLowerCase()+"ServiceImpl");
        wfService.sayUse();
    }


}
