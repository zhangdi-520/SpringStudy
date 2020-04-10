package com.example.springboot.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class DistributionLock {
    private static final Logger logger = LoggerFactory.getLogger(DistributionLock.class);

    private StringRedisTemplate stringRedisTemplate;

    private String lockPrefix ;

    public DistributionLock(StringRedisTemplate stringRedisTemplate, String lockPrefix) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockPrefix = lockPrefix;
    }

    /**
     * 获取分布式锁
     *
     * @param lockName           竞争获取锁key
     * @param acquireTimeoutInMS 获取锁超时时间
     * @param lockTimeoutInMS    锁的超时时间
     * @return 获取锁标识
     */
    public String acquireLockWithTimeout(String lockName,
                                         long acquireTimeoutInMS, long lockTimeoutInMS) {
        logger.info("尝试获取锁,锁名:{}", lockName);
        String retIdentifier = null;

        String identifier = UUID.randomUUID().toString();
        String lockKey = lockPrefix + lockName;


        long end = System.currentTimeMillis() + acquireTimeoutInMS;

        try {
            BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps(lockKey);
            while (System.currentTimeMillis() < end) {
                boolean success = boundValueOperations.setIfAbsent(identifier);
                if (success) {
                    boundValueOperations.expire(lockTimeoutInMS, TimeUnit.MILLISECONDS);
                    retIdentifier = identifier;
                    return retIdentifier;
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return retIdentifier;
    }

    /**
     * 释放锁
     *
     * @param lockName   竞争获取锁key
     * @param identifier 释放锁标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifier) {
        logger.info("尝试释放锁,锁名:{}", lockName);
        String lockKey = lockPrefix + lockName;
        boolean retFlag = true;

        try {
            BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps(lockKey);
            if (identifier.equals(boundValueOperations.get())) {
                stringRedisTemplate.delete(lockKey);
                retFlag = true;
            }
        } catch (Exception e) {
            retFlag = false;
        }

        return retFlag;
    }
}


