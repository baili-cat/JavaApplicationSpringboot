package com.example.test.xskytest.rocketmq.producer.annotation;

import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.SelectorType;

import java.lang.annotation.*;

/**
 * RocketMQ消费者注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RocketMqConsumer {
    /**
     * 消费者组
     * @return
     */
    String consumerGroup();

    /**
     * 订阅topic主题
     * @return
     */
    String topic();

    /**
     * 消息选择器类型，默认：tag
     * @return
     */
    SelectorType selectorType() default SelectorType.TAG;

    /**
     * 选择器表达式，默认：“*”
     * @return
     */
    String selectorExpress() default "*";

    /**
     * 消费模式，默认：多线程并发消费
     * @return
     */
    ConsumeMode consumeMode() default ConsumeMode.CONCURRENTLY;

    /**
     * 消费方式，默认：集群消费
     * @return
     */
    MessageModel messageMode() default MessageModel.CLUSTERING;

    /**
     * 最大消费者线程数量，默认：64
     * @return
     */
    int consumeThreadMax() default 64;

}
