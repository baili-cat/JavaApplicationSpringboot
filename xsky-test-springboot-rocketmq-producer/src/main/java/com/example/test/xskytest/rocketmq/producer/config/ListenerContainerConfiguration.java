//package com.example.test.xskytest.rocketmq.producer.config;
//
//import com.example.test.xskytest.rocketmq.producer.annotation.RocketMqConsumer;
//import com.example.test.xskytest.rocketmq.producer.constant.RocketMqConstants;
//import com.example.test.xskytest.rocketmq.producer.core.DefaultRocketMqListenerContainer;
//import com.example.test.xskytest.rocketmq.producer.core.RocketMqConsumerListener;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.springframework.aop.support.AopUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.env.StandardEnvironment;
//
//import javax.annotation.Resource;
//import java.util.Map;
//import java.util.Objects;
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * @author baili
// * @date 2023年06月07日17:15
// */
//@Configuration
//@ConditionalOnClass(DefaultMQPushConsumer.class)
//@EnableConfigurationProperties(RocketMqProperties.class)
//@ConditionalOnProperty(prefix = "spring.rocketmq", value = "name-server")
//@Slf4j
//public class ListenerContainerConfiguration implements ApplicationContextAware, InitializingBean {
//
//    /**
//     * 上下文
//     */
//    private ConfigurableApplicationContext applicationContext;
//
//    /**
//     * 计数器
//     */
//    private AtomicLong counter = new AtomicLong(0);
//
//    /**
//     * 运行环境
//     */
//    @Resource
//    private StandardEnvironment environment;
//
//    /**
//     * RocketMq属性配置
//     */
//    @Resource
//    private RocketMqProperties rocketMqProperties;
//
//    public ListenerContainerConfiguration() {
//        System.out.println("1111");
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext context) throws BeansException {
//        //设置上下文
//        this.applicationContext = (ConfigurableApplicationContext) context;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        //获取配置了RocketMQMessageListener消费者监听注解的类信息
//        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(RocketMqConsumer.class);
//        if(Objects.nonNull(beans)){
//            beans.forEach(this::registerMessageListener);
//        }
//    }
//
//    /**
//     * 注册消息监听器
//     * @param beanName bean名称
//     * @param bean bean对象
//     */
//    private void registerMessageListener(String beanName, Object bean){
//        Class<?> clazz = AopUtils.getTargetClass(bean);
//
//        if(!RocketMqConsumerListener.class.isAssignableFrom(bean.getClass())){
//            throw new IllegalStateException("rocketMq " + clazz + " is not instance of " + RocketMqConsumerListener.class.getName());
//        }
//
//        RocketMqConsumerListener rocketMqConsumerListener = (RocketMqConsumerListener) bean;
//        //获取注解
//        RocketMqConsumer annotation = clazz.getAnnotation(RocketMqConsumer.class);
//        BeanDefinitionBuilder beanBuilder = BeanDefinitionBuilder.rootBeanDefinition(DefaultRocketMqListenerContainer.class);
//        //nameserver寻址
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_NAMESERVER, rocketMqProperties.getNameServer());
//        //topic主题
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_TOPIC, environment.resolvePlaceholders(annotation.topic()));
//        //消费者组
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_CONSUMER_GROUP, environment.resolvePlaceholders(annotation.consumerGroup()));
//        //消费模式（并发消费或顺序消费）
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_CONSUME_MODE, annotation.consumeMode());
//        //最大消费线程数
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_CONSUME_THREAD_MAX, annotation.consumeThreadMax());
//        //消费方式（集群消费或广播消费）
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_MESSAGE_MODEL, annotation.messageMode());
//        //选择器表达式
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_SELECTOR_EXPRESS, environment.resolvePlaceholders(annotation.selectorExpress()));
//        //选择器类型《tag）
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_SELECTOR_TYPE, annotation.selectorType());
//        //消费者监听器
//        beanBuilder.addPropertyValue(RocketMqConstants.PROP_ROCKETMQ_CONSUMER_LISTENER, rocketMqConsumerListener);
//        //销毁方法
//        beanBuilder.setDestroyMethodName(RocketMqConstants.METHOD_DESTROY);
//
//        String containerBeanName = String.format("%s_%s", DefaultRocketMqListenerContainer.class.getName(), counter.incrementAndGet());
//        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
//        beanFactory.registerBeanDefinition(containerBeanName, beanBuilder.getBeanDefinition());
//
//        DefaultRocketMqListenerContainer container = beanFactory.getBean(containerBeanName, DefaultRocketMqListenerContainer.class);
//        if(!container.isStarted()){
//            try {
//                container.start();
//            } catch (MQClientException e) {
//                log.error("rocketMq started container failed. {}", container, e);
//                throw new RuntimeException(e);
//            }
//        }
//        log.info("rocketMq register consumer listener to container, beanName:{}, containerBeanName:{}", beanName, containerBeanName);
//    }
//}
