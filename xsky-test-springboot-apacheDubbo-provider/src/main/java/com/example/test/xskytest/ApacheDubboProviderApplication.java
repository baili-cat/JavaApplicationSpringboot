package com.example.test.xskytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.test.xskytest"})
public class ApacheDubboProviderApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ApacheDubboProviderApplication.class, args);
    }

}
