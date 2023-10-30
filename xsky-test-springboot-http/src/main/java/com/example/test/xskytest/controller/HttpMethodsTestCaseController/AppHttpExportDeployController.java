package com.example.test.xskytest.controller.HttpMethodsTestCaseController;

import com.alibaba.fastjson.JSONObject;
import com.example.test.xskytest.utils.HttpRequestUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author baili
 * @date 2023年03月30日16:00
 */

@RestController
@CrossOrigin
@RequestMapping("/AppExportDeploy/")
public class AppHttpExportDeployController {
    Logger logger = LoggerFactory.getLogger(AppHttpExportDeployController.class);

    @GetMapping("redisExportDeploy")
    public String redisExportDeploy(@RequestParam(value = "hostIp", defaultValue = "10.10.x.x:8080") String hostIp,
                                    @RequestParam(value = "startDeployNumber", defaultValue = "1") String startDeployNumber,
                                    @RequestParam(value = "endDeployNumber", defaultValue = "1") String endDeployNumber) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //String redisExportDeplouApi = "http://" + hostIp + "/dockerDeploy/middleware";
        String redisExportDeplouApi = "http://10.10.223.38:11012/dockerDeploy/middleware";
        int startNumberInt = Integer.parseInt(startDeployNumber);
        int endNumberInt = Integer.parseInt(endDeployNumber);
        Integer initPort = 6379;
        for (int i = startNumberInt; i <= endNumberInt; i++) {
            initPort++;
            String requestBody = "{\n" +
                    "  \"middlewareDockerConfig\": {\n" +
                    "    \"appName\": \"redis" + i + "\",\n" +
                    "    \"dockerComposeReplaceKeyValue\": [\n" +
                    "      {\"serviceName\": \"redis" + i + "\"},\n" +
                    "      {\"serverPort\": \"" + initPort + "\"},\n" +
                    "      {\"hostname\": \"" + hostIp + "_" + initPort + "\"},\n" +
                    "      {\"dockerPort\":\"6379\"},\n" +
                    "      {\"imageName\":\"docker.baili-inc.com/baili-test/bullseye_redis:redis_7.0.10\"},\n" +
                    "      {\"redis_password\":\"Baili888.\"},\n" +
                    "      {\"node_exporter_path\": \"/nodeExport/app-one-exporter\"},\n" +
                    "      {\"exporter_enable\": \"true\"},\n" +
                    "      {\"baili_xcenter_server\": \"10.10.202.60:17951\"},\n" +
                    "      {\"baili_xcenter_orgcode\": \"BaiLi\"},\n" +
                    "      {\"baili_app_server\": \"10.10.202.60:39001\"},\n" +
                    "      {\"baili_collector_server\": \"10.10.202.60:16022\"}\n" +
                    "    ],\n" +
                    "    \"middlewareDeployPath\": \"/data/service\",\n" +
                    "    \"middlewareDockerImageUrl\": \"http://ftp-project.baili-inc.com/baili/redis/redis_images/bullseye_redis-redis_7.0.10-amd64.tar.gz\",\n" +
                    "    \"middlewareDockerTemplatesUrl\": \"http://ftp-project.baili-inc.com/baili/redis/docker-templates/redisDockerTemplates-node.tar.gz\",\n" +
                    "    \"nodeExportUrl\":\"http://ftp.baili-inc.com/xfiregod/delivery/resource/app-one-exporter/1.0.0-SNAPSHOT/app-one-exporter-1.0.0-SNAPSHOT.tar.gz\"\n" +
                    "  },\n" +
                    "  \"serverConfig\": {\n" +
                    "    \"connectTimeout\": 0,\n" +
                    "    \"host\": \"" + hostIp + "\",\n" +
                    "    \"osType\": \"linux\",\n" +
                    "    \"password\": \"baili1234\",\n" +
                    "    \"port\": 22,\n" +
                    "    \"user\": \"root\"\n" +
                    "  }\n" +
                    "}";
            JSONObject body = JSONObject.parseObject(requestBody);
            System.out.printf(body.toJSONString());
            System.out.println("=========================");
            //部署redis包
            int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("开始部署:" + finalI);
                    HttpRequestUtil.doPost(redisExportDeplouApi, body);
                }
            });

        }

        return "部署成功";
    }

    @GetMapping("redisExportStart")
    public String redisExportStart(@RequestParam(value = "hostIp", defaultValue = "10.10.x.x") String hostIp,
                                   @RequestParam(value = "startStartNumber", defaultValue = "1") String startStartNumber,
                                   @RequestParam(value = "startEndNumber", defaultValue = "1") String startEndNumber) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        String redisExportStartApi = "http://10.10.223.38:11012/dockerDeploy/middlewareStateChanges";
        int startNumberInt = Integer.parseInt(startStartNumber);
        int endNumberInt = Integer.parseInt(startEndNumber);
        for (int i = startNumberInt; i <= endNumberInt; i++) {
            String requestBody = "{\n" +
                    "  \"middlewareDockerConfig\": {\n" +
                    "    \"actionType\":\"start\",\n" +
                    "    \"appName\": \"redis" + i + "\",\n" +
                    "    \"middlewareDeployPath\": \"/data/service\"\n" +
                    "  },\n" +
                    "  \"serverConfig\": {\n" +
                    "    \"connectTimeout\": 0,\n" +
                    "    \"host\": \"" + hostIp + "\",\n" +
                    "    \"osType\": \"linux\",\n" +
                    "    \"password\": \"baili1234\",\n" +
                    "    \"port\": 22,\n" +
                    "    \"user\": \"root\"\n" +
                    "  }\n" +
                    "}";
            JSONObject body = JSONObject.parseObject(requestBody);
            System.out.println(redisExportStartApi);
            System.out.println("=========================");
            System.out.printf(body.toJSONString());
            System.out.println("=========================");
            //启动redis包
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    HttpRequestUtil.doPost(redisExportStartApi, body);
                }
            });

        }

        return "启动成功";
    }

    @GetMapping("redisExportStop")
    public String redisExportStop(@RequestParam(value = "hostIp", defaultValue = "10.10.x.x") String hostIp,
                                  @RequestParam(value = "stopStartNumber", defaultValue = "1") String stopStartNumber,
                                  @RequestParam(value = "stopEndNumber", defaultValue = "1") String stopEndNumber) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        String redisExportStartApi = "http://10.10.223.38:11012/dockerDeploy/middlewareStateChanges";
        int startNumberInt = Integer.parseInt(stopStartNumber);
        int endNumberInt = Integer.parseInt(stopEndNumber);
        for (int i = startNumberInt; i <= endNumberInt; i++) {
            String requestBody = "{\n" +
                    "  \"middlewareDockerConfig\": {\n" +
                    "    \"actionType\":\"remove\",\n" +
                    "    \"appName\": \"redis" + i + "\",\n" +
                    "    \"middlewareDeployPath\": \"/data/service\"\n" +
                    "  },\n" +
                    "  \"serverConfig\": {\n" +
                    "    \"connectTimeout\": 0,\n" +
                    "    \"host\": \"" + hostIp + "\",\n" +
                    "    \"osType\": \"linux\",\n" +
                    "    \"password\": \"baili1234\",\n" +
                    "    \"port\": 22,\n" +
                    "    \"user\": \"root\"\n" +
                    "  }\n" +
                    "}";
            JSONObject body = JSONObject.parseObject(requestBody);
            System.out.println(redisExportStartApi);
            System.out.println("=========================");
            System.out.printf(body.toJSONString());
            System.out.println("=========================");
            //部署redis包
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    HttpRequestUtil.doPost(redisExportStartApi, body);
                }
            });

        }

        return "启动成功";
    }

    @GetMapping("test")
    public String test(@RequestParam(value = "hostIp", defaultValue = "10.10.x.x:8080") String hostIp,
                                    @RequestParam(value = "startDeployNumber", defaultValue = "1") String startDeployNumber,
                                    @RequestParam(value = "endDeployNumber", defaultValue = "1") String endDeployNumber) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //String redisExportDeplouApi = "http://" + hostIp + "/dockerDeploy/middleware";
        String redisExportDeplouApi = "http://10.10.223.38:11012/dockerDeploy/middleware";
        int startNumberInt = Integer.parseInt(startDeployNumber);
        int endNumberInt = Integer.parseInt(endDeployNumber);
        Integer initPort = 6379;
        for (int i = startNumberInt; i <= endNumberInt; i++) {
            initPort++;
            String requestBody = "{\n" +
                    "      {\"serviceName\": \"redis" + i + "\"},\n" +
                    "      {\"serverPort\": \"" + initPort + "\"},\n";

            //JSONObject body = JSONObject.parseObject(requestBody);
            //System.out.printf(body.toJSONString());

            //部署redis包
            int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("=========================");
                    System.out.println(requestBody);
                    System.out.println("开始部署:" + finalI);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    //HttpRequestUtil.doPost(redisExportDeplouApi, body);
                }
            });

        }

        return "部署成功";
    }

}
