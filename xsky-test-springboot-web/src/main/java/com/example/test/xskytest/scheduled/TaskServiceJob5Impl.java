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
public class TaskServiceJob5Impl implements TaskService {

    @Autowired
    HttpTestControllerApplication httpTestControllerApplication;

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Override
    public void HandlerJob() {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        try {
            log.info("------job5 开始执行---------：" + new Date());
            log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  任务五启动");
            log.info(httpTestControllerApplication.jedisNormal(cotrollerFuction.getHttpServletRequest()));
        } catch (Exception e) {
            log.error("job5 执行异常：{}", e.toString());
        }

        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  job5  结束");
    }

    @Override
    public Integer jobId() {
        return 5;
    }

    @Override
    public String methodName() {
        return "httpTestControllerApplication.jedisNormal";
    }
}
