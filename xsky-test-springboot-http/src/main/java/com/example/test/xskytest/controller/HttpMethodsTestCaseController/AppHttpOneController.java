package com.example.test.xskytest.controller.HttpMethodsTestCaseController;

import com.example.test.xskytest.utils.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baili
 * @date 2023年03月28日14:28
 */
@RestController
@CrossOrigin
@RequestMapping("/AppOne/")
public class AppHttpOneController {
    Logger logger = LoggerFactory.getLogger(HttpGetController.class);

    @GetMapping("returnBody1")
    public String returnBody1(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody2")
    public String returnBody2(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");

        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody3")
    public String returnBody3(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody4")
    public String returnBody4(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody5")
    public String returnBody5(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody6")
    public String returnBody6(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody7")
    public String returnBody7(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody8")
    public String returnBody8(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody9")
    public String returnBody9(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody10")
    public String returnBody10(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody11")
    public String returnBody11(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody12")
    public String returnBody12(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody13")
    public String returnBody13(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody14")
    public String returnBody14(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody15")
    public String returnBody15(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody16")
    public String returnBody16(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody17")
    public String returnBody17(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody18")
    public String returnBody18(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody19")
    public String returnBody19(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }

    @GetMapping("returnBody20")
    public String returnBody20(@RequestParam(value = "responseTimeMs", defaultValue = "1") String responseTimeMs,
                              @RequestParam(value = "hostIp", defaultValue = "localhost") String hostIp,
                              @RequestParam(value = "callNumber", defaultValue = "1") String callNumber) throws InterruptedException {
        ArrayList<Map<String, String>> request = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("Name", "AppV5");
        map.put("Description", "description");
        request.add(map);
        long responseTimeMsTotal = Long.parseLong(responseTimeMs);
        int call = Integer.parseInt(callNumber);
        for (int i = 1; i < call; i++) {
            //http请求
            HttpRequestUtil.doGet("http://" + hostIp + "/httpController/returnString");
            //logger.info("http://" + hostIp + "/httpController/returnString");
        }
        Thread.sleep(responseTimeMsTotal);
        return request.toString();
    }
}
