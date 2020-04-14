package com.example.springboot.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-13 16:36
 **/
@Aspect
@Component
public class MyLogAspect {

    @Pointcut("@annotation(com.example.springboot.annotation.MyLog)")
    public void logPointCut(){};

    @Around("logPointCut()")
    public void logAround(ProceedingJoinPoint joinPoint){

        String methodName = joinPoint.getSignature().getName();

        Object[] param = joinPoint.getArgs();

        StringBuilder sb = new StringBuilder();
        for(Object o:param){
            sb.append(o+";");
        }
        System.out.println("进入["+methodName+"]方法,参数为:"+sb.toString());

        try {
            String result = (String) joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(methodName+"方法执行结束");

    }
}
