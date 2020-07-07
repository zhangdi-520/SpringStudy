package com.example.springboot.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-05-19 09:27
 **/
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
                System.out.println("ConfirmCallback:     " + "确认情况：" + b);
                System.out.println("ConfirmCallback:     " + "原因：" + s);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("ReturnCallback:     "+"消息："+message);
                System.out.println("ReturnCallback:     "+"回应码："+i);
                System.out.println("ReturnCallback:     "+"回应信息："+s);
                System.out.println("ReturnCallback:     "+"交换机："+s1);
                System.out.println("ReturnCallback:     "+"路由键："+s2);
            }
        });

        return rabbitTemplate;
    }
}
