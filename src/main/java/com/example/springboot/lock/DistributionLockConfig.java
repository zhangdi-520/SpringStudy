package com.example.springboot.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class DistributionLockConfig {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Bean
    public DistributionLock distributionLock(){
        return new DistributionLock(stringRedisTemplate,"nz_lock");
    }
}
