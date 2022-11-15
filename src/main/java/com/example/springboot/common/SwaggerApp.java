package com.example.springboot.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-04-20 16:43
 **/
@Configuration
@EnableSwagger2
public class SwaggerApp {

    private String aaa = "杨培兴";

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot.shiro.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Spring Boot 使用 Swagger2 构建RESTful API")
                .contact(new Contact("ZHANGDI","http://blog.bianxh.top/",""))
                .version("1.0")
                .description("API 描述")
                .build();
    }
}
