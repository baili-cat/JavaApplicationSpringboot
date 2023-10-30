package com.example.test.xskytest.scheduled;

import com.example.test.xskytest.common.constants.Constants;
import com.example.test.xskytest.apacheDubboService.timedtask.MyTaskService;
import com.example.test.xskytest.apacheDubboService.timedtask.TaskService;
import com.example.test.xskytest.utils.CotrollerFuction;
import com.example.test.xskytest.web.HttpTestExceptionControllerApplication;
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
public class TaskServiceJob704Impl implements TaskService {

    @Autowired
    HttpTestExceptionControllerApplication httpTestExceptionControllerApplication;

    @Autowired
    CotrollerFuction cotrollerFuction;

    @Override
    public void HandlerJob() {
        MDC.put(Constants.TRACE_LOG_ID, UUID.randomUUID().toString());
        try {
            log.info("------job704 开始执行---------：" + new Date());
            log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  任务七零四启动");
            log.info(httpTestExceptionControllerApplication.dubboAlibabaException(cotrollerFuction.getHttpServletRequest()));
        } catch (Exception e) {
            log.error("job704 执行异常：{}", e.toString());
        }

        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "  " + Thread.currentThread().getName() + "  job704  结束");
    }

    @Override
    public Integer jobId() {
        return 704;
    }

    @Override
    public String methodName() {
        return "httpTestExceptionControllerApplication.dubboAlibabaException";
    }
}
