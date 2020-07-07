package com.example.springboot.context;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class WfServiceImpl implements WfService, ApplicationRunner {
    @Override
    public void sayUse() {
        System.out.println("成功获取sping上下文");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("容器启动完成了，开始进行配置.....");
        System.out.println("配置完成");
    }
}
