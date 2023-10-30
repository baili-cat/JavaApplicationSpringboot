package com.example.test.xskytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author baili
 * @date 2022年10月31日11:29
 */
@SpringBootApplication(scanBasePackages = "com.example.test.xskytest")
@EnableAutoConfiguration
public class TestOpsMockUndertowApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder(TestOpsMockUndertowApplication.class).build();
        app.run(args);
    }
}
