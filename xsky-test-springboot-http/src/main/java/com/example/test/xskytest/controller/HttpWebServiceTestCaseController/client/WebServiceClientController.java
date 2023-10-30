//package com.example.test.xskytest.controller.HttpWebServiceTestCaseController.client;
//
//import com.example.test.xskytest.webservice.CxfService;
//import io.swagger.annotations.ApiOperation;
//import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author baili
// * @date 2023年06月11日17:59
// */
//@RestController
//@RequestMapping("/webService/xSkyTest/")
//public class WebServiceClientController {
//    @ApiOperation(value = "webService连接接口")
//    @GetMapping("webService")
//    public String webService() {
//        String address = "http://localhost:11013/webservice/TestOpsService?wsdl";
//
//        // 接口地址
////        String address = "http://localhost:8089/webservice/userService?wsdl";
//        // 代理工厂
//        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
//        // 设置代理地址
//        jaxWsProxyFactoryBean.setAddress(address);
//        // 设置接口类型
//        jaxWsProxyFactoryBean.setServiceClass(CxfService.class);
//        // 创建一个代理接口实现
//        CxfService cxf = (CxfService) jaxWsProxyFactoryBean.create();
//        // 调用代理接口的方法调用并返回结果
//        String message = cxf.sendMessage("test");
//        System.out.println("返回结果: " + message);
//        return message;
//    }
//}
