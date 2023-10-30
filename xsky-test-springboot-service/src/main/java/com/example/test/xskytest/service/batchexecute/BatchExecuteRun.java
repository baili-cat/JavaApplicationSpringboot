package com.example.test.xskytest.apacheDubboService.batchexecute;

import com.example.test.xskytest.common.constants.Constants;
import com.example.test.xskytest.apacheDubboService.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class BatchExecuteRun implements Runnable {

    private Map<String, Object> map = new HashMap<>();
    private Map<String, String> header = new HashMap<>();
    private String url;

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setHeader(Map<String, String> map) {
        this.header = header;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private void exec() throws Exception {

        HttpClientUtil httpClientUtil = HttpClientUtil.getInstance();
        // urlStr = "http://localhost:8092/httpGetHtml";
        String resultStr = httpClientUtil.doPost(url, map, header);
        log.info(resultStr);
    }


    @Override
    public void run() {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        try {
            //  Thread.sleep(300);
            exec();
        } catch (Exception e) {
            log.info("BatchMysqlAndRedisTestService 执行异常:{}", e.toString());
        }
    }

}
