package com.example.springboot.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @program: Spring
 * @description: Topic交换机
 * @author: Mr.Zhang
 * @create: 2020-05-18 16:20
 **/
@Configuration
public class TopicRabbitConfig {

    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    @Bean
    public Queue firstQueue(){
        return new Queue(TopicRabbitConfig.man);
    }

    @Bean
    public Queue secondQueue(){
        return new Queue(TopicRabbitConfig.woman);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    Binding bindingExchangeMessage2(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
