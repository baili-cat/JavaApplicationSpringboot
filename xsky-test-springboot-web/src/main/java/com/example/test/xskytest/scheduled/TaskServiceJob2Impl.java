package com.example.test.xskytest.scheduled;

import com.example.test.xskytest.common.constants.Constants;
import com.example.test.xskytest.apacheDubboService.timedtask.MyTaskService;
import com.example.test.xskytest.apacheDubboService.timedtask.TaskService;
import com.example.test.xskytest.utils.CotrollerFuction;
import com.example.test.xskytest.web.HttpTestControllerApplication;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@MyTaskService
public class TaskServiceJob2Impl implements TaskService {

    @Autowired
    HttpTestControllerApplication httpTestControllerApplication;

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Override
    public void HandlerJob() {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        try {
            log.info("------job2 开始执行---------：" + new Date());
            log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  任务二启动");
            log.info(httpTestControllerApplication.rocketmqNormal(cotrollerFuction.getHttpServletRequest()));
        } catch (Exception e) {
            log.error("job2 执行异常：{}", e.toString());
        }

        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  job2  结束");
    }

    @Override
    public Integer jobId() {
        return 2;
    }

    @Override
    public String methodName() {
        return "httpTestControllerApplication.rocketmqNormal";
    }
}
