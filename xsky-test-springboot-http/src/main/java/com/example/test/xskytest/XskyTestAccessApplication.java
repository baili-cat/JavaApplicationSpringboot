package com.example.test.xskytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author baili
 * @date 2022年10月31日11:29
 */
@SpringBootApplication(scanBasePackages = "com.example.test.xskytest")
@MapperScan("com.example.test.xskytest.dal.mapper")
@ConfigurationPropertiesScan
@EnableConfigurationProperties

public class XskyTestAccessApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder(XskyTestAccessApplication.class).build();
        app.run(args);
    }
}
