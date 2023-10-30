package com.example.test.xskytest.config;

import com.example.test.xskytest.cfx.server.api.CxfTestService;
import com.example.test.xskytest.cfx.server.impl.CxfTestServiceImpl;
import com.example.test.xskytest.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.xml.ws.Endpoint;


/*
 * WebServiceConfig配置发布服务
 * */
@Configuration
@Slf4j
public class WebServiceConfig {

    //WebServiced发布调用接口
    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        log.info("dispatcherServlet");
        return new ServletRegistrationBean(new CXFServlet(), "/services/*");//自己的WebService根访问路径
    }

    //支持Springboot注解调用接口
    @Bean
    public ServletRegistrationBean dispatcherRestServlet() {
        System.out.println("dispatcherRestServlet");
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //替换成自己的controller包路径
        context.scan("com.example.test.xskytest.web");
        DispatcherServlet disp = new DispatcherServlet(context);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(disp);
        registrationBean.setLoadOnStartup(1);
        //映射路径自定义,必须设置一个不重复的名称
        registrationBean.addUrlMappings("/*");//自己的Springboot注解访问的根路径
        registrationBean.setName("/");
        return registrationBean;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    //注册用户登录服务
    @Bean
    public CxfTestService LoginService() {
        return new CxfTestServiceImpl();
    }

    //发布登陆服务
    @Bean
    public Endpoint endpointLogin() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), LoginService());
        endpoint.publish("/UserSession");//接口前缀路径
        return endpoint;
    }
}
