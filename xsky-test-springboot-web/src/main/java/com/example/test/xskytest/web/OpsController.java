package com.example.test.xskytest.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by xiaoming on 16/3/14.
 * version:1.1 by lixin 添加io流关闭
 * 应用属性获取
 */
@Controller
@Slf4j
public class OpsController {

    private String opsInfo;

    public OpsController() {

        //这里使用try-with-resources语法，方法执行完毕后BufferedInputStream资源会自动关闭，不需要finally块
        //BufferedInputStream因为实现了Closeable接口
        try (BufferedInputStream inputStream = new BufferedInputStream(
            OpsController.class.getResourceAsStream("/healthcheck.html"))) {
            StringBuilder sb = new StringBuilder();
            byte[] line = new byte[2048];
            while (inputStream.read(line) != -1) {
                sb.append(new String(line));
            }
            opsInfo = sb.toString();
        } catch (FileNotFoundException e) {
            opsInfo = "ops info not exist";
        } catch (IOException e) {
            opsInfo = "ops info read error";
        }
    }

    /**
     * 应用打包相关信息
     *
     * @return project.version
     */
    @RequestMapping("/healthcheck.html")
    @ResponseBody
    public String healthCheck() {
        log.info(opsInfo);
        return opsInfo;
    }
}
