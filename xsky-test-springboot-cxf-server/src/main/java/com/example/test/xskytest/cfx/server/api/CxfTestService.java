package com.example.test.xskytest.cfx.server.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import com.alibaba.fastjson.JSONException;
/**
 *@name:接口的服务名称（自定）
 *@targetNamespace:接口命名空间（一般是接口包名的倒序）
 */
@WebService(name="CxfTestService",targetNamespace="http://cxf.xskytest.test.example.com")
public interface CxfTestService {

    @WebMethod(operationName="cxfTest",action="http://cxf.xskytest.test.example.com")//operationName：服务的接口名（发布的方法名）
    //@WebResult(name = "ns:return")//返回的标签
    @WebResult(name = "return")
    public String cxfTest(@WebParam(name = "username", targetNamespace = "http://cxf.xskytest.test.example.com")String username, @WebParam(name = "password", targetNamespace = "http://cxf.xskytest.test.example.com")String password) throws JSONException;


    @WebMethod(operationName="cxfTestException",action="http://cxf.xskytest.test.example.com")//operationName：服务的接口名（发布的方法名）
    //@WebResult(name = "ns:return")//返回的标签
    @WebResult(name = "return")
    public String cxfTestException(@WebParam(name = "username", targetNamespace = "http://cxf.xskytest.test.example.com")String username, @WebParam(name = "password", targetNamespace = "http://cxf.xskytest.test.example.com")String password) throws JSONException;

}
