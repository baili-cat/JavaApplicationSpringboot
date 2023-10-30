package com.example.test.xskytest.controller.HttpKafkaTestCaseController;

import com.example.test.xskytest.utils.StructureData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author baili
 * @date 2022年12月15日17:45
 */

@RestController
@Component
@RequestMapping("/kafka/xSkyTest/")
public class HttpKafka {
    Logger logger = LoggerFactory.getLogger(HttpKafka.class);
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.application.name}")
    private String groupId;

    @Value("${spring.kafka.consumer.topic}")
    private  String topic;
    @Resource
    KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("sendMessageString")
    public String sendMessageString(@RequestParam(value = "topicName", defaultValue = "baili_test") String topicName,
                                    @RequestParam(value = "stringLength", defaultValue = "1") String stringLength) {

        try {
            String mes = StructureData.accumulate(Integer.parseInt(stringLength));
            kafkaTemplate.send(topicName, mes);
            return "发送消息:" + mes + "成功";
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    //TODO 消费还有问题，后面优化，不影响使用
    @RequestMapping("consumerMessageString")
    public String consumerMessageString(@RequestParam(value = "topicName", defaultValue = "baili_test") String topicName,
                                        @RequestParam(value = "count", defaultValue = "1") String count) {

        try {
            Properties props = new Properties();

            props.put("bootstrap.servers", bootstrapServers);
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("group.id", groupId);
            KafkaConsumer<String, String> kafkaConsumer = null;
            try {
                kafkaConsumer = new KafkaConsumer<>(props);
                logger.info("kafka消费端连接成功");
                kafkaConsumer.subscribe(Collections.singleton(topicName));
                //for (int i = 1; i <= Integer.parseInt(count); ) {
                //while (true) {
                    ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {

                        System.out.println("消费" + consumerRecord.topic() + "-" + consumerRecord.partition() + "-" + consumerRecord.value());
                    }
                    //System.out.println(consumerRecords + "====================================");
                    //logger.info("第" + i + "次消费");
                    //i++;
                    //}
                //}

                return "持续消费:" + count + "分钟内消息成功";
            } catch (Exception e) {
                kafkaConsumer.close();
                logger.error("kafka消费端连接成功");
                throw new RuntimeException(e);
            }finally {
                kafkaConsumer.close();
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
