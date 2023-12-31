package com.example.test.xskytest.rocketmq.producer;

import com.example.test.xskytest.rocketmq.producer.core.MessageCallback;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * MQ消息发送模板类
 * @author baili
 * @date 2023/5/23 17:25
*/
@Slf4j
public class RocketMqSendTemplate implements InitializingBean, DisposableBean {

    /**
     * 生产者
     */
    private DefaultMQProducer producer = new DefaultMQProducer("bili_producer");
    /**
     * 同步发送MQ消息
     * @param message MQ消息内容
     * @return
     */
    public SendResult syncSend(Message message){
        if(Objects.isNull(message)){
            log.info("rocketMq syncSend failed, message is null...");
            throw new IllegalArgumentException("`message` cannot be null");
        }

        try {
            /**
             * TODO: 后面有时间再改成AOP
             */
            //发送开始时间
            long begin = System.currentTimeMillis();
            SendResult sendResult =  producer.send(message);
            //发送结束时间
            long end = System.currentTimeMillis();

            log.info("rocketMq syncSend message: {}, topic: {}, tags:{}, keys:{}, msgId: {}, cost: {}ms, sendResult: {}",
                    message, message.getTopic(), message.getTags(), message.getKeys(),
                    sendResult != null ? sendResult.getMsgId() : "", (end - begin), sendResult);

            return sendResult;
        } catch (Exception e) {
            log.error("rocketMq syncSend message error...{}", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 同步发送MQ消息，设置超时时间
     * @param message MQ消息内容
     * @param timeout 超时时间
     * @return
     */
    public SendResult syncSend(Message message, long timeout){
        if(Objects.isNull(message)){
            log.info("rocketMq syncSend failed, message is null...");
            throw new IllegalArgumentException("`message` cannot be null");
        }

        try {
            //发送开始时间
            long begin = System.currentTimeMillis();
            SendResult sendResult = producer.send(message, timeout);
            //发送结束时间
            long end = System.currentTimeMillis();

            log.info("rocketMq syncSend message: {}, topic: {}, tags:{}, keys:{}, msgId: {}, cost: {}ms, sendResult: {}",
                    message, message.getTopic(), message.getTags(), message.getKeys(),
                    sendResult != null ? sendResult.getMsgId() : "", (end - begin), sendResult);

            return sendResult;
        } catch (Exception e) {
            log.info("rocketMq syncSend message failed...{}", message);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 同步发送MQ消息，扩展失败重发
     * @param message MQ消息内容
     * @param retryTimes 重发次数
     * @return
     */
    public SendResult syncSend(Message message, int retryTimes){
        if(Objects.isNull(message)){
            log.info("rocketMq syncSend failed, message is null...");
            throw new IllegalArgumentException("`message` cannot be null");
        }

        int count = 0;
        while (count < retryTimes){
            SendResult sendResult = syncSend(message);
            if(null != sendResult && (SendStatus.SEND_OK == sendResult.getSendStatus())){
                log.info("rocketMq syncSend message, sendResult: {}", sendResult);
                return sendResult;
            }else {
                count++;
            }
        }

        return null;
    }

    /**
     * 发送一次性消息
     * @param message MQ消息内容
     */
    public void sendOneWay(Message message){
        if(Objects.isNull(message)){
            log.info("rocketMq sendOneWay failed, message is null...");
            throw new IllegalArgumentException("`message` cannot be null");
        }

        try {
            //发送开始时间
            long begin = System.currentTimeMillis();
            producer.sendOneway(message);
            //发送结束时间
            long end = System.currentTimeMillis();

            log.info("rocketMq sendOneWay message: {}, topic: {}, tags:{}, keys:{}, cost: {} ms",
                    message, message.getTopic(), message.getTags(), message.getKeys(), (end - begin));

        } catch (Exception e) {
            log.error("rocketMq sendOneWay message error...{}", e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 发送延时消息
     * @param message 消息内容
     * @param delayLevel 延时级别 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * @return
     */
    public boolean sendDelayMessage(Message message, int delayLevel){
        if(Objects.isNull(message)){
            log.info("rocketMq send delay failed, message is null...");
            throw new IllegalArgumentException("`message` cannot be null");
        }

        /* 设置延时发送级别 */
        message.setDelayTimeLevel(delayLevel);
        SendResult sendResult = null;
        try {
            //发送开始时间
            long begin = System.currentTimeMillis();
            sendResult = producer.send(message);
            //发送结束时间
            long end = System.currentTimeMillis();

            log.info("rocketMq sendDelay message: {}, topic: {}, tags:{}, keys:{}, msgId: {}, cost: {}ms, sendResult: {}",
                    message, message.getTopic(), message.getTags(), message.getKeys(),
                    sendResult != null ? sendResult.getMsgId() : "", (end - begin), sendResult);

        } catch (Exception e) {
            log.error("rocketMq send delay message error...{}", e);
        }

        if(null != sendResult && (sendResult.getSendStatus() == SendStatus.SEND_OK)){
            return true;
        }
        return false;
    }

    /**
     * 异步发送消息
     * @param message 消息内容
     * @param callback 异步回调
     */
    public void sendAsynMessage(Message message, final MessageCallback callback) {
        if(Objects.isNull(message)){
            log.info("rocketMq sendAsync failed, message is null...");
            throw new IllegalArgumentException("`message` cannot be null");
        }

        try {
            //发送开始时间
            long begin = System.currentTimeMillis();
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    callback.callback(sendResult);
                }

                @Override
                public void onException(Throwable e) {
                    log.error("rocketMq sendAsync message callback error...{}", e);
                }
            });
            //发送结束时间
            long end = System.currentTimeMillis();

            log.info("rocketMq sendAsync message: {}, topic: {}, tags:{}, keys:{}, cost: {} ms",
                    message, message.getTopic(), message.getTags(), message.getKeys(), (end - begin));

        } catch (Exception e) {
            log.error("rocketMq sendAsync message error...{}", e);
        }
    }

    /**
     * 异步发送消息
     * @param message MQ消息内容
     */
    public void sendAsyncMessage(Message message) {
        sendAsynMessage(message, new MessageCallback() {
            @Override
            public void callback(SendResult result) {
                if(log.isInfoEnabled()){
                    log.info("rocketMq sendAsync message success! message: {}", result == null ? "" : result.toString());
                }
            }
        });
    }

    @Override
    public void destroy() throws Exception {
        if(Objects.nonNull(producer)){
            //关闭生产者实例
            producer.shutdown();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //启动生产者实例
        Assert.notNull(producer, "Property 'producer' is required");
        producer.start();
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public void setProducer(DefaultMQProducer producer) {
        this.producer = producer;
    }
}
