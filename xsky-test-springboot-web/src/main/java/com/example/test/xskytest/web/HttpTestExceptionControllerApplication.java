package com.example.test.xskytest.web;



import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.test.xskytest.cfx.client.CxfClient;
import com.example.test.xskytest.common.constants.CustomErrorEnum;
import com.example.test.xskytest.common.exception.CustomException;
import com.example.test.xskytest.dal.service.TestCaseService;
import com.example.test.xskytest.dubbo.api.request.RequestDTO;
import com.example.test.xskytest.dubbo.consumer.XskyTestDubboCall;
import com.example.test.xskytest.redis.JedisServiceImpl;
import com.example.test.xskytest.rocketmq.producer.MQProducerService;
import com.example.test.xskytest.apacheDubboService.timedtask.annotation.MyTestException;
import com.example.test.xskytest.utils.CotrollerFuction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class HttpTestExceptionControllerApplication {


    @Autowired
    CotrollerFuction cotrollerFuction;


    @Autowired
    XskyTestDubboCall xskyTestDubboCall;


    @Autowired
    CxfClient     cxfClient;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JedisServiceImpl jedisServiceImpl;

    @Autowired
    MQProducerService mqProducerService;

    @RequestMapping(value="/http/customerror/exception", method = {RequestMethod.GET,RequestMethod.POST}
    ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestException
    public String httpCustomerrortException(HttpServletRequest httpRequest) throws Exception {
        Map<String, String> map =  cotrollerFuction.printMessage(httpRequest);
        throw new CustomException(CustomErrorEnum.CUSTOM_ERROR,"自定义异常");
    }

    /**
     *  调用Apache dubbo test
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value = "/dubbo/apache/exception" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @MyTestException
    public String dubboApacheException(HttpServletRequest httpRequest) {

            cotrollerFuction.printMessage(httpRequest);
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setPlatformName("测试");
            requestDTO.setTraceLogId("1111223344");
            requestDTO.setOrderCode("111111");
            requestDTO.setOrderName("Xsky-test-springboot");
            requestDTO.setPayAmount(22222);
            requestDTO.setOrderRemark("订单备注 Apache Exception");
            return     "dubboAlibabaTestException异常：{}" + xskyTestDubboCall.dubboApacheException(requestDTO);
    }


    /**
     *  调用Apache dubbo test
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value = "/dubbo/apache/timeout" , method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @MyTestException
    public String dubboApacheTimeout(HttpServletRequest httpRequest) {

        cotrollerFuction.printMessage(httpRequest);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPlatformName("测试");
        requestDTO.setTraceLogId("1111223344");
        requestDTO.setOrderCode("111111");
        requestDTO.setOrderName("Xsky-test-springboot");
        requestDTO.setPayAmount(22222);
        requestDTO.setOrderRemark("订单备注 Apache Timeout");
        return     "dubboAlibabaTestException异常：{}" + xskyTestDubboCall.dubboApacheTimeout(requestDTO);
    }

    /**
     *  调用Alibaba dubbo test
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value="/dubbo/alibaba/exception", method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestException
    public String dubboAlibabaException( HttpServletRequest httpRequest)  {
        cotrollerFuction.printMessage(httpRequest);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPlatformName("测试");
        requestDTO.setTraceLogId("1111223344");
        requestDTO.setOrderCode("111111");
        requestDTO.setOrderName("Xsky-test-springboot");
        requestDTO.setPayAmount(22222);
        requestDTO.setOrderRemark("订单备注 Alibaba Exception");
        return  "dubboAlibabaTestException异常：{}" + xskyTestDubboCall.dubboAlibabaException(requestDTO);

    }

    /**
     *  调用Alibaba dubbo test
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value="/dubbo/alibaba/timeout", method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestException
    public String dubboAlibabaTimeout( HttpServletRequest httpRequest)  {
        cotrollerFuction.printMessage(httpRequest);
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setPlatformName("测试");
        requestDTO.setTraceLogId("1111223344");
        requestDTO.setOrderCode("111111");
        requestDTO.setOrderName("Xsky-test-springboot");
        requestDTO.setPayAmount(22222);
        requestDTO.setOrderRemark("订单备注 Alibaba Timeout");
        return  "dubboAlibabaTestException异常：{}" + xskyTestDubboCall.dubboAlibabaTimeout(requestDTO);

    }


    /**
     *  调用cxf  web server
     *
     * 2022年8月6日 bailiconstants
     * exception
     */
    @RequestMapping(value = "/cxf/exception", method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestException
    public String cxfException(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        return  cxfClient.getLoginException();
    }

    /**
     *  调用cxf  web server
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value = "/sql/exception", method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestException
    public String sqlException(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);

        return  "全表慢sql查询：select * from  test_case" + "  "+  testCaseService.mysqlException();
    }


    /**
     *  调用jedis
     *
     * 2022年8月6日 baili
     */
    @RequestMapping(value = "/jedis/exception", method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestException
    public String jedisException(HttpServletRequest httpRequest) throws  Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        log.info(jedisServiceImpl.setString("testkey","11122",60000l));

        log.info(jedisServiceImpl.setStringException("testkey","11122","60000"));
        return  jedisServiceImpl.getString("testkey");
    }

    /**
     * 发送消息
     *
     * 2022年8月6日 baili
     * @throws InterruptedException
     */
    @RequestMapping(value = "/rocketmq/exception", method = {RequestMethod.GET,RequestMethod.POST}
            ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    @MyTestException
    public String rocketmqException(HttpServletRequest httpRequest) throws Exception {
        Map<String, String> map = cotrollerFuction.printMessage(httpRequest);
        return  mqProducerService.sendMsgException();
    }

}
