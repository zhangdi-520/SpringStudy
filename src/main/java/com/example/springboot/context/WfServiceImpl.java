package com.example.springboot.context;

import org.springframework.stereotype.Service;

@Service
public class WfServiceImpl implements WfService{
    @Override
    public void sayUse() {
        System.out.println("成功获取sping上下文");
    }
}
