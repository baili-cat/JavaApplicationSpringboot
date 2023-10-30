package com.example.test.xskytest.controller.HttpRabbitMqTestCaseController;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.example.xskytest.rabbitmq.config.DirectRabbitMqConfiguration.*;

/**
 * @author baili
 * @date 2023年05月18日15:43
 */
@Component
@RabbitListener(bindings = {@QueueBinding(value = @Queue(value = QUEUE_NAME), exchange = @Exchange(value = EXCHANGE_NAME), key = ROUTING_KEY)})
public class RabbitMqConsumerMessageLicenter {
    @RabbitHandler
    public void process(Map testMessages) {
        System.out.println("DirectReceiver消费者收到消息:" + testMessages.toString());
    }
}
