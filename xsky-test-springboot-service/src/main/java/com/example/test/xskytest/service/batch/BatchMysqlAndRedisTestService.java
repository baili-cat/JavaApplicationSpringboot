package com.example.test.xskytest.apacheDubboService.batch;

import com.example.test.xskytest.common.constants.Constants;
import com.example.test.xskytest.dal.moduleDO.TestCaseDO;
import com.example.test.xskytest.dal.service.TestCaseService;
import com.example.test.xskytest.redis.JedisServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BatchMysqlAndRedisTestService {

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JedisServiceImpl jedisServiceImpl;

    private List<TestCaseDO> mysqlAndRedisTest(String testCaseCode) {
        String result = "";
        List<TestCaseDO> list = testCaseService.select_Test_Case(testCaseCode);
        //  jedisServiceImpl.setString("testkey","1122aabb",600l);

        return list;
    }

    public String working(String testCaseCode) {
        try {
            //  Thread.sleep(300);
            List<TestCaseDO> list = mysqlAndRedisTest(testCaseCode);
            log.info("数据库查询返回 ：{}", list.toString());
            jedisServiceImpl.setString("testKey" + testCaseCode, list.get(0).getTestCaseRemark(), 600l);
            Thread.sleep(50l);
            jedisServiceImpl.getString("testKey" + testCaseCode);
            return "BatchMysqlAndRedisTestService.working  执行完成！";

        } catch (Exception e) {
            log.info("BatchMysqlAndRedisTestService 执行异常:{}", e.toString());
            return "BatchMysqlAndRedisTestService 执行异常:{}" + e.toString();
        }
    }
}
