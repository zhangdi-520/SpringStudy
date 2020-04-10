package com.example.springboot.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static  ApplicationContext getApplicationContext(){
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.context = applicationContext;
    }

    public static Object getBean(String beanId){
        if(context.containsBean(beanId)){
            return context.getBean(beanId);
        }
        return null;
    }
}
