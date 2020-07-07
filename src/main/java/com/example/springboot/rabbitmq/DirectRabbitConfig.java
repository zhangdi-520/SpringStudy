package com.example.springboot.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @program: Spring
 * @description: 直连型交换机
 * @author: Mr.Zhang
 * @create: 2020-05-18 14:34
 **/
@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue TestDirectQueue(){
        return new Queue("TestDirectQueue",true);
    }

    @Bean
    DirectExchange TestDirectExchange(){
        return new DirectExchange("TestDirectExchange",true,false);
    }

    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }

    @Bean
    DirectExchange lonelyDirectExchange(){
        return new DirectExchange("lonelyDirectExchange");
    }


}
