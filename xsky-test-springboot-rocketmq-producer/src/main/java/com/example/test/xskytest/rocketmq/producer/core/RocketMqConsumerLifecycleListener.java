package com.example.test.xskytest.rocketmq.producer.core;

/**
 * MQ消费者生命周期监听接口
 * Created by feizi on 2018/6/26.
 */
public interface RocketMqConsumerLifecycleListener<T> {

    /**
     * 预启动
     * @param consumer
     */
    void prepareStart(final T consumer);
}
