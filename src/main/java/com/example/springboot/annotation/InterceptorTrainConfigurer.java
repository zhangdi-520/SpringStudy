package com.example.springboot.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version V1.0
 * @program: Spring
 * @description: 拦截器配置类
 * @author: Mr.Zhang
 * @create: 2020-04-13 15:52
 **/
@Configuration
public class InterceptorTrainConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SourceAccessInterceptor()).addPathPatterns("/source/**");
    }
}
