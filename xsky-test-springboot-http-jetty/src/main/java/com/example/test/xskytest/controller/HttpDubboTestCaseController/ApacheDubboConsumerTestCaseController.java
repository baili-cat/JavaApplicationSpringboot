package com.example.test.xskytest.controller.HttpDubboTestCaseController;


import com.example.test.xskytest.apacheDubboService.DubboProviderService;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baili
 * @date 2022年11月07日16:18
 */
@RestController
@RequestMapping("/dubbo/apache/")
public class ApacheDubboConsumerTestCaseController {
    Logger logger = LoggerFactory.getLogger(ApacheDubboConsumerTestCaseController.class);


    @DubboReference(version = "1.0.0")
    DubboProviderService dubboProviderService;

    @ApiOperation(value = "apacheDubbo接口，调用时需要部署provider")
    @GetMapping("sayHello")
    public String sayHello(@RequestParam(value = "custom", defaultValue = "默认输出") String custom) throws Exception {
        logger.info("apacheDubbo调用sayHello:" + custom);
        return dubboProviderService.sayHello(custom);
    }


}
