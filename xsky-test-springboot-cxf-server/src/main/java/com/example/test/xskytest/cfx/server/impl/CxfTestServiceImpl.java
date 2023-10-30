package com.example.test.xskytest.cfx.server.impl;

import javax.jws.WebService;

import com.alibaba.fastjson.JSONException;
import com.example.test.xskytest.cfx.server.api.CxfTestService;
import com.example.test.xskytest.common.constants.CustomErrorEnum;
import com.example.test.xskytest.common.exception.CustomException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
/*
 * @name:接口的服务名称
 * @targetNamespace:接口命名空间
 * @endpointInterface:接口地址
 *
 * */
@Slf4j
@WebService(name="UCxfTestService",
        targetNamespace="http://cxf.xskytest.test.example.com",
        endpointInterface= "com.example.test.xskytest.cfx.server.api.CxfTestService")
@Component
@EnableAutoConfiguration
public class CxfTestServiceImpl implements CxfTestService {

    @Override
    public  String cxfTest(String username, String password){
        boolean bool = false;
        if("admin".equals(username) && "123456".equals(password)){
            bool = true;
        }
        return String.valueOf(bool);
    }

    @Override
    public String cxfTestException(String username, String password) throws JSONException {
        boolean bool = false;
        if("admin".equals(username) && "123456".equals(password)){
            bool = true;
        }
        throw new CustomException(CustomErrorEnum.CUSTOM_ERROR,"自定义CXF服务异常");
}
}
