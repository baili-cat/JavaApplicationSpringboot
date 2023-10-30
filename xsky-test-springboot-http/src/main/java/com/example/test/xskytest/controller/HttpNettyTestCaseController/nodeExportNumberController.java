package com.example.test.xskytest.controller.HttpNettyTestCaseController;

import com.example.test.xskytest.httpserver.configuration.NettyHttpServerConfig;
import com.example.test.xskytest.httpserver.netty.iohandler.HttpServerHandler;
import com.example.test.xskytest.httpserver.netty.iohandler.InterceptorHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author baili
 * @date 2022年12月09日17:46
 */
@RestController
public class nodeExportNumberController {
    Logger logger = LoggerFactory.getLogger(nodeExportNumberController.class);

    private static NettyHttpServerConfig nettyHttpServerConfig = new NettyHttpServerConfig();
    @Resource
    private InterceptorHandler interceptorHandler;

    @Resource
    private HttpServerHandler httpServerHandler;

    @GetMapping("stopAllNettyServer")
    public String stopAllNettyServer() {
        try {
            nettyHttpServerConfig.closeServer();
            return "关闭所有nettyServer" ;
        } catch (Exception e) {
            e.printStackTrace();
            return "关闭所有nettyServer出错，异常信息：" + e.toString();
        }
    }

    @GetMapping("startNettyServer")
    public String startNettyServer(String ports) {
        try {
            nettyHttpServerConfig.start(ports, interceptorHandler, httpServerHandler);
            return "启动nettyServer：" + ports;
        } catch (Exception e) {
            return "启动nettyServer出错，异常信息：" + e.toString();
        }
    }

    @GetMapping("/createNodeExport")
    public String createNodeExport(String hostIp, String startPort, String portCount) {
        if (StringUtils.isEmpty(hostIp)) {
            return "请求参数hostIp不允许为空";
        }
        int startPortActually;
        if (StringUtils.isEmpty(startPort)) {
            startPortActually = 10000;
        } else {
            startPortActually = Integer.parseInt(startPort);
        }
        StringBuilder ports = new StringBuilder();
        StringBuilder xSkyExportJob = new StringBuilder();
        xSkyExportJob.append("{\"name\":\"test_baili\",\"path\":\"\",\"models\":[{\"exporterType\":\"HOST\"," +
                "\"ipPorts\":[");
        for (int i = 1; i <= Integer.parseInt(portCount); i++) {
            ports.append(startPortActually);
            xSkyExportJob.append("\n{\"ip\":\"" + hostIp + "\",\"port\":\"" + startPortActually + "\"," +
                    "\"path" +
                    "\":\"/metrics" +
                    "\"}");
            if (i != Integer.parseInt(portCount)) {
                ports.append(",");
                xSkyExportJob.append(",");
            }
            startPortActually++;

        }
        xSkyExportJob.append("\n],\"labels\":[]}]}");
        //StringUtils.removeEnd(String.valueOf(ports),",");
        System.out.println(ports);
        ////String ports = "11111,11112,11113";

        nettyHttpServerConfig.start(ports.toString(), interceptorHandler, httpServerHandler);

        return xSkyExportJob.toString();
    }

    @GetMapping("/createNodeExportData")
    public String createNodeExportData(String hostIp, String startPort, String portCount) {
        if (StringUtils.isEmpty(hostIp)) {
            return "请求参数hostIp不允许为空";
        }
        int startPortActually;
        if (StringUtils.isEmpty(startPort)) {
            startPortActually = 10000;
        } else {
            startPortActually = Integer.parseInt(startPort);
        }
        StringBuilder ports = new StringBuilder();
        StringBuilder xSkyExportJob = new StringBuilder();
        xSkyExportJob.append("{\"name\":\"test_baili\",\"path\":\"\",\"models\":[{\"exporterType\":\"HOST\"," +
                "\"ipPorts\":[");
        List<String> hostIPs = Arrays.asList("10.10.202.40", "10.10.202.45", "10.10.202.46", "10.10.202.47", "10.10.202" +
                        ".48", "10.10.202.49", "10.10.202.50", "10.10.202.22", "10.10.202.56", "10.10.202.53",
                "10.10.202.63", "10.10.202.68", "10.10.202.26", "10.10.202.27", "10.10.202.28", "10.10.202.29", "10" +
                        ".10.202.65", "10.10.202.60", "10.10.202.61", "10.10.202.32");
        for (int i = 1; i <= Integer.parseInt(portCount); i++) {
            ports.append(startPortActually);
            for (String postIp1 : hostIPs) {
                xSkyExportJob.append("\n{\"ip\":\"" + postIp1 + "\",\"port\":\"" + startPortActually + "\"," +
                        "\"path" +
                        "\":\"/metrics" +
                        "\"}");
                if (i != Integer.parseInt(portCount)) {
                    ports.append(",");
                    xSkyExportJob.append(",");
                }
            }


            startPortActually++;

        }
        xSkyExportJob.append("\n],\"labels\":[]}]}");
        //StringUtils.removeEnd(String.valueOf(ports),",");
        System.out.println(ports);
        ////String ports = "11111,11112,11113";
        //NettyHttpServerConfig nettyHttpServerConfig = new NettyHttpServerConfig();
        //nettyHttpServerConfig.nettyHttpServerConfig(ports.toString(), interceptorHandler, httpServerHandler);

        return xSkyExportJob.toString();
    }
}

