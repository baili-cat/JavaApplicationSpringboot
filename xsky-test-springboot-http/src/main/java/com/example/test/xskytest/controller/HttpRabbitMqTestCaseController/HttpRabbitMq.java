package com.example.test.xskytest.controller.HttpRabbitMqTestCaseController;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.example.xskytest.rabbitmq.config.DirectRabbitMqConfiguration.*;

/**
 * @author baili
 * @date 2023年05月18日15:36
 */
@RestController
@Component
@RequestMapping("/rabbitMq/xSkyTest/")
public class HttpRabbitMq {

    @Resource
    RabbitTemplate rabbitTemplate;

    @GetMapping("sendMessage")
    public String sentMessage(@RequestParam(value = "message", defaultValue = "rabbitMq_baili") String message) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("message", message);
            System.out.println(rabbitTemplate.getExchange() + "#############");
            System.out.println(rabbitTemplate.getRoutingKey() + "#############");
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "rabbitMq Message has been sent";
    }

}
