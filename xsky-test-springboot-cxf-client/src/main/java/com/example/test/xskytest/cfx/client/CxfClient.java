package com.example.test.xskytest.cfx.client;

import javax.xml.namespace.QName;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidHost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

@Service
@Slf4j
public class CxfClient {

    @Autowired
    ServerConfig serverConfig;

    public  String  getLogin() throws Exception {

        String wsdl= serverConfig.getUrl() + "/services/UserSession?wsdl";
        //最后的斜杠不能少
        String targetNamespace = "http://cxf.xskytest.test.example.com";
        String methodName ="cxfTest";
        String username="admin";
        String password="123456";
        Object[] params=new Object[]{username,password};
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdl);

        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        objects = client.invoke(new QName(targetNamespace, methodName), username,password);
        log.info("返回数据:" + objects[0]);
       return  JSONObject.toJSONString(objects,
               SerializerFeature.WriteClassName,
               SerializerFeature.SortField,
               SerializerFeature.WriteNullStringAsEmpty);
    }

    public  String  getLoginException() throws Exception {
        String wsdl=serverConfig.getUrl()  + "/services/UserSession?wsdl";
        //最后的斜杠不能少
        String targetNamespace = "http://cxf.xskytest.test.example.com";
        String methodName ="cxfTestException";
        String username="admin";
        String password="123456";
        Object[] params=new Object[]{username,password};
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(wsdl);

        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
        Object[] objects;
        objects = client.invoke(new QName(targetNamespace, methodName), username,password);
        log.info("返回数据:" + objects[0]);
        return  JSONObject.toJSONString(objects,
                SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }
}