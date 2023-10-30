package com.example.test.xskytest.controller.HttpMethodsTestCaseController;

import com.example.test.xskytest.utils.StructureData;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baili
 * @date 2022年11月02日16:57
 */
@RestController
@CrossOrigin
@RequestMapping("/httpController/")
//纯http请求
public class HttpGetController {
    Logger logger = LoggerFactory.getLogger(HttpGetController.class);

    /**
     * @param description get返回list
     * @author baili
     * @date 2022/11/2 17:00
     */
    @ApiOperation(value = "get请求,返回当前时间以及自定义描述信息等list")
    @GetMapping("returnList")
    public ArrayList returnList(@RequestParam(value = "description", defaultValue = "TestOps平台~") String description) {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        map.put("Date", dateFormat.format(date));
        map.put("Name", "TestOps");
        map.put("Description", description);
        if (description.equals("TestOps平台~")){
            logger.info("description为："+ description );
        }else if (description.equals("elseif")){
            logger.info("description为："+ description );
        }
        request.add(map);
        logger.info("http的get请求返回ArrayList");
        return request;
    }

    /**
     * @param stringLength get返回String
     * @author baili
     * @date 2022/11/2 17:00
     */
    @ApiOperation(value = "get请求,返回自定义字符串，字符串长度可通过参数stringLength指定，默认1")
    @GetMapping("returnString")
    public String returnString(@RequestParam(value = "stringLength", defaultValue = "1") String stringLength) {
        if (stringLength.equals("1")){
            logger.info("stringLength为："+ stringLength );
        }else{
            logger.info("stringLength太长了，长度为："+ stringLength );
        }
        logger.info("http的get请求返回String");
        return StructureData.accumulate(Integer.parseInt(stringLength));
    }

    /**
     * @param exceptionDetails 返回异常信息
     * @author baili
     * @date 2022/11/2 17:00
     */
    @ApiOperation(value = "get请求,返回异常，异常信息可通过参数exceptionDetails指定")
    @GetMapping("returnException")
    public String returnException(@RequestParam(value = "exceptionDetails", defaultValue = "默认异常") String exceptionDetails) throws Exception {
        if (exceptionDetails.equals("1")){
            logger.info("exceptionDetails："+ exceptionDetails );
        }else{
            logger.info("exceptionDetails，长度为："+ exceptionDetails );
        }
        throw new Exception("exceptionDetails");
    }

    /**
     * @param responseTimeMs 返回异常信息
     * @author baili
     * @date 2022/11/2 17:00
     */
    @ApiOperation(value = "get请求,接口返回响应时间延迟，可通过参数responseTimeMs指定，默认延迟1ms")
    @GetMapping("returnResponseTimeController")
    public String returnResponseTimeController(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs) throws Exception {
        if (responseTimeMs.equals("1")){
            logger.info("responseTimeMs："+ responseTimeMs );
        }else{
            logger.info("responseTimeMs，长度为："+ responseTimeMs );
        }
        logger.info("http的get请求返回响应时间异常");
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        Thread.sleep(responseTimeMsTotal);
        return "响应时间为：" + responseTimeMs + "ms左右";
    }

}
