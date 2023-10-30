package com.example.test.xskytest.rocketmq.producer.core;

import org.springframework.beans.factory.DisposableBean;

public interface RocketMqConsumerListenerContainer extends DisposableBean {

    /**
     * 注册MQ消费者监听器
     * @param mqConsumerListener
     */
    void registerConsumerListener(RocketMqConsumerListener<?> mqConsumerListener);
}
