package com.example.test.xskytest.rocketmq.producer.core;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * 消息回调
 * @author baili
 * @date 2023/5/23 18:05
*/
public interface MessageCallback {
    void callback(SendResult result);
}
