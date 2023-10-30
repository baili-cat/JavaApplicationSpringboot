package com.example.test.xskytest.controller.HttpRocketMqTestCaseController;

import com.example.test.xskytest.rocketmq.producer.annotation.RocketMqConsumer;
import com.example.test.xskytest.rocketmq.producer.entity.MessageData;
import org.apache.rocketmq.common.message.Message;
import com.example.test.xskytest.rocketmq.producer.core.RocketMqConsumerListener;
import com.example.test.xskytest.rocketmq.producer.util.JsonUtils;
import com.example.test.xskytest.rocketmq.producer.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author baili
 * @date 2023年05月18日15:43
 */
@Slf4j
@Component
@RocketMqConsumer(consumerGroup = "testOpsGroup", topic = "testOpsTopicName")
public class AutoConsumer implements RocketMqConsumerListener<Message> {


    @Override
    public boolean consume(Message message) {
        log.info("==========================AutoConsumer start=====================");

        if (Objects.isNull(message)) {
            //接收到空消息，也表明此次消费成功
            return true;
        }
        log.info("AutoConsumer received message: {}, topic: {}, tags: {}, keys: {}",
                message, message.getTopic(), message.getTags(), message.getKeys());

        //取出消息体
        byte[] messageBody = message.getBody();
        if (Objects.isNull(messageBody)) {
            //接收到空消息，也表明此次消费成功
            return true;
        }

        /**
         * TODO 具体的业务逻辑
         */

        log.info("==========================AutoConsumer end=====================");
        return true;
    }
}
