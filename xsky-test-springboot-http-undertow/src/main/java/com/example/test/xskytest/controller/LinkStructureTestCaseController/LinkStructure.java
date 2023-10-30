package com.example.test.xskytest.controller.LinkStructureTestCaseController;

import com.example.test.xskytest.utils.HttpRequestUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author baili
 * @date 2022年12月14日11:46
 */

@RestController
@CrossOrigin
@RequestMapping("/linkStructureController/")
public class LinkStructure {
    Logger logger = LoggerFactory.getLogger(LinkStructure.class);

    /**
     * @param callNumber 调用次数
     * @author baili
     * @date 2022/11/2 17:00
     */
    @ApiOperation(value = "get请求,通过参数调整单条链路中调用次数,hostIp需要指定调用的接口ip默认localhost,docker部署时可能会访问不通，callNumber调用次数")
    @GetMapping("MultipleLink")
    public String MultipleLink(@RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                               @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) {
        for (int i = 0; i < Integer.parseInt(callNumber); i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            logger.info("http://" + hostIp + "/httpController/returnString");
        }
        return "一条链路中http调用" + Integer.parseInt(callNumber) + "次";
    }

    @ApiOperation(value = "get请求,通过参数调整单条链路中包含哪些中间件")
    @GetMapping("MultipleLinkMiddleware")
    public String MultipleLinkMiddleware(@RequestParam(value = "hostIp", defaultValue = "10.10.x.x:8080") String hostIp,
                                         @RequestParam(value = "middlewareNames", defaultValue = "mysql,redis") String middlewareNames,
                                         @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) {
        String redisUrl = "http://" + hostIp + "/redis/jedis/queryKey";
        String mysqlUrl = "http://" + hostIp + "/mysql/xSkyTest/checkTableExists";
        String kafkaUrl = "http://" + hostIp + "/kafka/xSkyTest/sendMessageString";
        StringBuilder requestContent = new StringBuilder();
        for (int i = 0; i < Integer.parseInt(callNumber); i++) {
            if (middlewareNames.toLowerCase().contains("mysql")) {//http请求
                HttpRequestUtil.doGet(mysqlUrl);
                requestContent.append("mysql-");
            }
            if (middlewareNames.toLowerCase().contains("redis")) {
                HttpRequestUtil.doGet(redisUrl);
                requestContent.append("redis-");
            }
            if (middlewareNames.toLowerCase().contains("kafka")) {
                HttpRequestUtil.doGet(kafkaUrl);
                requestContent.append("kafka-");
            }
        }

        return "一条链路中调用包含中间件" + requestContent + ",共调用" + callNumber + "次";
    }
}
