package com.example.test.xskytest.scheduled;

import com.example.test.xskytest.common.constants.Constants;
import com.example.test.xskytest.apacheDubboService.timedtask.MyTaskService;
import com.example.test.xskytest.apacheDubboService.timedtask.TaskService;
import com.example.test.xskytest.apacheDubboService.timedtask.annotation.MyTestNormal;
import com.example.test.xskytest.utils.CotrollerFuction;
import com.example.test.xskytest.web.HttpTestControllerApplication;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@MyTaskService
public class TaskServiceJob1Impl implements TaskService {

    @Autowired
    HttpTestControllerApplication httpTestControllerApplication;

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Override
    public void HandlerJob() {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        try {
            log.info("------job1 开始执行---------：" + new Date());
            log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  任务一启动");
            log.info(httpTestControllerApplication.httpPostTimeout(cotrollerFuction.getHttpServletRequest()));
        } catch (Exception e) {
            log.error("job1 执行异常：{}", e.toString());
        }

        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  job1  结束");
    }

    @Override
    public Integer jobId() {
        return 1;
    }

    @Override
    public String methodName() {
        return "httpTestControllerApplication.httpPostTimeout";
    }
}
