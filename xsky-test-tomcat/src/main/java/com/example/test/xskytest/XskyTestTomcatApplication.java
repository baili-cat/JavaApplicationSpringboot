package com.example.test.xskytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author baili
 * @date 2022年10月31日11:29
 */
@SpringBootApplication(scanBasePackages = "com.example.test.xskytest")
public class XskyTestTomcatApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplicationBuilder(XskyTestTomcatApplication.class).build();
        app.run(args);
    }
}
