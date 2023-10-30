package com.example.test.xskytest.controller.HttpMethodsTestCaseController;

import com.alibaba.fastjson.JSONObject;
import com.example.test.xskytest.common.utils.Jackson;
import com.example.test.xskytest.utils.StructureData;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author baili
 * @date 2023年03月30日16:00
 */

@RestController
@CrossOrigin
@RequestMapping("/AppErrorKey/")
public class AppHttpErrorKeyController {
    Logger logger = LoggerFactory.getLogger(AppHttpErrorKeyController.class);

    @GetMapping("returnErrorKeyNotRepeat")
    public String returnErrorKeyNotRepeat(@RequestParam(value = "responseStringLength", defaultValue = "1") String responseStringLength) {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("Name", "AppV5");
        jsonResponse.put("Description", RandomStringUtils.randomAlphanumeric(Integer.parseInt(responseStringLength)));
        return jsonResponse.toJSONString();
    }

    @GetMapping("returnErrorKeyRepeat")
    public String returnErrorKeyRepeat()  {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("Name", "AppV5");
        jsonResponse.put("Description","Description" );
        return jsonResponse.toJSONString();
    }
}
