package com.example.test.xskytest;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableDubbo
//@DubboComponentScan(basePackages = "com.example.test.xskytest.dubbo")
@ComponentScan({"com.example.test.xskytest.**"})
@SpringBootConfiguration
@SpringBootApplication
@MapperScan("com.example.test.xskytest.dal.*")
@EnableScheduling // 开启定时
public class TestInterfaceWebApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        //setRegisterErrorPageFilter(false);
        return application.sources(TestInterfaceWebApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(TestInterfaceWebApplication.class, args);

    }


}
