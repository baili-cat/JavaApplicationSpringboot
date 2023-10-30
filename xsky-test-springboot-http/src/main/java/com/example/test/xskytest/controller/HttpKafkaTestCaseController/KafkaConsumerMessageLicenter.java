package com.example.test.xskytest.controller.HttpKafkaTestCaseController;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author baili
 * @date 2023年03月20日23:55
 */
@Slf4j
@Component
public class KafkaConsumerMessageLicenter {

    //@KafkaListener(topics = "#{'{spring.kafka.consumer.topic}'}")
    @KafkaListener(topics = {"baili_test"})
    public void consumer(ConsumerRecord<Integer, String> record) {
        log.info("kafka process msg start");
        log.info("processMessage, topic = {}, msg={}", record.topic(), record.value());
        log.info("kafka process msg end");
    }
}
