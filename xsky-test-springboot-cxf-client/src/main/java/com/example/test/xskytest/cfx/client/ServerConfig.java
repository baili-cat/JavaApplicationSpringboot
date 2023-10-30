package com.example.test.xskytest.cfx.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidHost;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
@Component
public class ServerConfig implements ApplicationListener<ApplicationReadyEvent> {



    @Value("${server.context-path}")
    private String contextPath;


    @Value("${server.port}")
    private int serverPort;

    public String getUrl() {

        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "http://"+address.getHostAddress() +":"+this.serverPort + contextPath;
    }

    public String getContextPath() {

        return  contextPath;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("项目启动完成");
    }
}
