package com.example.test.xskytest.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName JedisConfig
 * @Description TODO
 * @Author baili
 * @Version 1.0
 */
@Configuration
public class JedisConfig {

    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port:7090}")
    private int port;

    @Value("${spring.redis.password:Baili888.}")
    private String password;
    @Value("${spring.redis.timeout:3000}")
    private int timeout;

    @Value("${spring.redis.jedis.pool.max-active:10}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.max-idle:6}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle:2}")
    private int minIdle;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);

        logger.info("JedisPool连接成功:" + host + "\t" + port);

        return jedisPool;
    }
}