package com.example.test.xskytest.web;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.xskytest.cfx.client.CxfClient;
import com.example.test.xskytest.cfx.client.ServerConfig;
import com.example.test.xskytest.common.constants.Constants;
import com.example.test.xskytest.dal.service.TestCaseService;
import com.example.test.xskytest.dubbo.consumer.XskyTestDubboCall;
import com.example.test.xskytest.dubbo.api.request.RequestDTO;
import com.example.test.xskytest.dubbo.api.request.TempUserDto;
import com.example.test.xskytest.redis.JedisServiceImpl;
import com.example.test.xskytest.rocketmq.producer.MQProducerService;
import com.example.test.xskytest.apacheDubboService.timedtask.annotation.MyTestNormal;
import com.example.test.xskytest.utils.CotrollerFuction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@Slf4j
public class HttpTestControllerApplication {

    @Autowired
    MQProducerService mqProducerService;


    @Autowired
    CxfClient     cxfClient;


    @Autowired
    TestCaseService testCaseService;

    @Autowired
    XskyTestDubboCall xskyTestDubboCall;

    @Autowired
    JedisServiceImpl jedisServiceImpl;

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Autowired
    ServerConfig   serverConfig;


    @RequestMapping(value = "/", method = {RequestMethod.GET,
            RequestMethod.POST},
            produces = "application/json;charset=UTF-8")
    public String localhost(Model model , HttpServletRequest httpRequest) throws Exception {

        model.addAttribute("urlPath",serverConfig.getUrl());
        model.addAttribute("contextPath",serverConfig.getContextPath());
        return "test";
    }

    @ResponseBody
    @RequestMapping(value="/http/httpGetJson" ,
            method = {RequestMethod.GET,RequestMethod.POST},produces="application/json;charset=UTF-8")
    public String httpGetJson(Model model, HttpServletRequest httpRequest, HttpServletResponse response) {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());

        //打印请求内容
        Map<String,String> parmMap = cotrollerFuction.printMessage(httpRequest);
        Map<String,String> map = new HashMap<String,String>();
        map.put("key1","test1");
        map.put("key2","test2");
        return  JSONObject.toJSONString(map,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteClassName,
                SerializerFeature.SortField,
                SerializerFeature.WriteNullStringAsEmpty);
    }


    @RequestMapping("/http/dto/getRequestUserjson")
    public @ResponseBody TempUserDto httpGetRequestUserjson(@RequestBody TempUserDto user,HttpServletRequest httpRequest ){
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        log.info(user.toString());
        return user;
    }

    /**
     * 发送消息
     *
     * 2022年8月6日 baili
     * @throws InterruptedException
     * @throws MQBrokerException
     * @throws RemotingException
     * @throws MQClientException
     */
    @RequestMapping("/rocketmq/normal")
    @ResponseBody
    @MyTestNormal
    public String rocketmqNormal(HttpServletRequest httpRequest) throws MQClientException, RemotingException, MQBrokerException, InterruptedException{
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        return  mqProducerService.sendMsg();
    }


    /**
     *  调用cxf  web server
     *
     * 2022年8月6日 bailiconstants
     * exception
     */
    @RequestMapping(value = "/cxf/normal",  method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestNormal
    public String cxfNormal(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        return  cxfClient.getLogin();
    }


    /**
     *  调用cxf  web server
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value = "/sql/slow",  method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @MyTestNormal
    public String sqlSlow(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);

        return  "全表慢sql查询：select * from  test_case" + "  "+  testCaseService.getSlowSQL();

    }


    /**
     *  调用Apache dubbo test
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value =  "/dubbo/apache/normal",  method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestNormal
    public String dubboApacheNormal(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPlatformName("测试");
        requestDTO.setTraceLogId("1111223344");
        requestDTO.setOrderCode("111111");
        requestDTO.setOrderName("Xsky-test-springboot");
        requestDTO.setPayAmount(22222);
        requestDTO.setOrderRemark("订单备注 Apache");

        return  "Dubbo Apache  返回："+xskyTestDubboCall.dubboApacheProve(requestDTO);
    }




    /**
     *  调用Alibaba dubbo test
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value = "/dubbo/alibaba/normal",  method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestNormal
    public String dubboAlibabaNormal(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPlatformName("测试");
        requestDTO.setTraceLogId("1111223344");
        requestDTO.setOrderCode("111111");
        requestDTO.setOrderName("Xsky-test-springboot");
        requestDTO.setPayAmount(22222);
        requestDTO.setOrderRemark("订单备注 Alibaba");

        return  "Dubbo Alibaba  返回："+xskyTestDubboCall.dubboAlibabaProve(requestDTO);
    }


    /**
     *  调用jedis
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value = "/jedis/normal",  method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestNormal
    public String jedisNormal(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
       log.info(jedisServiceImpl.setString("testkey","11122",60000l));


        return  jedisServiceImpl.getString("testkey");
    }


    @RequestMapping(value="http/post/timeout", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @MyTestNormal
    public String httpPostTimeout(HttpServletRequest httpRequest) throws Exception {
        String timeout = "1000";

        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        if(map.containsKey("timeout") && StringUtils.isNotEmpty(map.get("timeout"))){
            timeout = map.get("timeout");}
        long start = System.currentTimeMillis();
        log.info("超时睡眠睡觉开始"+ start);
        Thread.sleep(Long.valueOf(timeout));
        long takeUpTime = System.currentTimeMillis() - start;
        log.info(String.format("共计耗时{%s}毫秒", takeUpTime));
        return  "响应超时设定时间：" + timeout  + "耗时：" + takeUpTime;
    }

    @RequestMapping("/test")
    String test(Model model) {
        model.addAttribute("users", "testttt");

        return "result";
    }

}
