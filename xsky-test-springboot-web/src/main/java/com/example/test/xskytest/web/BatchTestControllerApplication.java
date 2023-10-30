package com.example.test.xskytest.web;


import com.example.test.xskytest.apacheDubboService.batchexecute.BatchExecuteRun;
import com.example.test.xskytest.cfx.client.ServerConfig;
import com.example.test.xskytest.dal.service.TestCaseService;
import com.example.test.xskytest.redis.JedisServiceImpl;
import com.example.test.xskytest.apacheDubboService.batch.BatchMysqlAndRedisTestService;
import com.example.test.xskytest.apacheDubboService.batch.emum.BatchTypeUrlEnum;
import com.example.test.xskytest.apacheDubboService.utils.ThreadPoolUtil;
import com.example.test.xskytest.utils.CotrollerFuction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class BatchTestControllerApplication {

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JedisServiceImpl jedisServiceImpl;


    @Autowired
    CotrollerFuction cotrollerFuction;

    @Autowired
    BatchMysqlAndRedisTestService     batchMysqlAndRedisTestService;


    @Autowired
    ServerConfig serverConfig;



    @RequestMapping(value = "/transfer/batch", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public String test(Model model) throws Exception {
        model.addAttribute("contextPath",serverConfig.getContextPath());
        return "batch";
    }
    /**
     * <p>
     * 2022年8月6日 baili
     * 10s中发送1000条trace，采样率：90%，每条trace有一次mysql调用，一次redis调用
     * @throws  Exception
     */
    @RequestMapping(value = "/batch/mysql/redis/test", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String batchMysqlRedisTest(HttpServletRequest httpRequest) throws Exception {

        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        if (parmMap.containsKey("index") && StringUtils.isNotEmpty(parmMap.get("index"))) {
            return   batchMysqlAndRedisTestService.working(parmMap.get("index"));
        }
        else {
            return  "缺少参数 index";
        }

    }

    /**
     *  并发调用接口
     * <p>
     * 2022年8月6日 baili
     * 10s中发送1000条trace，采样率：90%，每条trace有一次mysql调用，一次redis调用
     * @throws  Exception
     */
    @RequestMapping(value = "/batch/{code}/{count}/execute", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String batchExecute( @PathVariable  Integer code, @PathVariable Integer count,HttpServletRequest httpRequest) throws Exception {
        Map<String, String> parmMap = cotrollerFuction.printMessage(httpRequest);
        Long execTime = null;
        try {
            long start = System.currentTimeMillis();
            //发送10次消息
            for (Integer i = 1; i <count + 1; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("index", String.valueOf(i));
                Map<String, String> header = new HashMap<>();
              //  header.put("Content-Type", "application/json");
                try {
                    BatchExecuteRun batchExecuteRun = new BatchExecuteRun();
                    batchExecuteRun.setMap(map);
                    batchExecuteRun.setHeader(header);
                    batchExecuteRun.setUrl(serverConfig.getUrl() + BatchTypeUrlEnum.getDesc(code));
                    //apiOptionService.setInt(i);
                  ThreadPoolUtil.execute(batchExecuteRun);
                } catch (Exception e) {
                    log.info(e.toString());
                    e.printStackTrace();
                }
            }
             execTime= System.currentTimeMillis() - start;
            log.info(String.format("共计耗时{%s}毫秒", execTime));

       /*     try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        } catch (Exception e) {
            log.info("创建测试用例异常：{}", e.toString());

        }
        return  "执行code：" +  String.valueOf(code)  + "  执行次数：" + String.valueOf(count) + "   执行时间：{"+ String.valueOf(execTime) + "}毫秒";
    }
}
