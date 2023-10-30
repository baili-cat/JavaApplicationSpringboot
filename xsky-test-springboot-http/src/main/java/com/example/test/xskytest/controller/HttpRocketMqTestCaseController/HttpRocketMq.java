package com.example.test.xskytest.controller.HttpRocketMqTestCaseController;

import com.example.test.xskytest.rocketmq.producer.RocketMqSendTemplate;
import com.example.test.xskytest.rocketmq.producer.util.MessageUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baili
 * @date 2023年05月18日15:36
 */
@RestController
@Component
@RequestMapping("/rocketMq/xSkyTest/")
public class HttpRocketMq {

    @Resource
    private RocketMqSendTemplate rocketMqSendTemplate;

    @GetMapping("sendMessage")
    public String sentMessage(@RequestParam(value = "messageString", defaultValue = "rocketMq_TestOps_messageString") String messageString,
                              @RequestParam(value = "topicName", defaultValue = "testOpsTopicName") String topicName) {
        try {
            Message message = MessageUtils.buildMessage(topicName, "TestOps", "TestOps_key", messageString);
            SendResult sendResult = rocketMqSendTemplate.syncSend(message);
            //            rocketMqSendTemplate.sendAsyncMessage(message);
//            return  "rocketMq Message has been sent";
            if (ObjectUtils.isNotEmpty(sendResult)) {
                return "rocketMq Message has been sent";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "rocketMq Message send failed";
    }

}
