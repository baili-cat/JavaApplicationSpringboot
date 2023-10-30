package com.example.test.xskytest.controller.HttpMysqlTestCaseController;

import com.example.test.xskytest.dal.service.DataInitializationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baili
 * @date 2022年12月16日16:20
 */
@RestController
@RequestMapping("/mysql/xSkyTest/")
public class HttpMysqlNotUrl {
    Logger logger = LoggerFactory.getLogger(HttpMysqlNotUrl.class);
    @Resource
    private DataInitializationService dataInitializationService;
    /**
     * @return java.util.List<com.example.test.xskytest.dal.moduleDO.TestCaseDO>
     * @author baili
     * @date 2022/11/4 10:41
     */
    @ApiOperation(value = "运行sql脚本，暂时执行当前应用初始化需要的sql，后续可扩展为远程执行sql")
    @GetMapping("runSqlScript")
    public String runSqlScript(@RequestParam(value = "url", defaultValue = "jdbc:mysql://10.10.202.11:3306/") String url,
                               @RequestParam(value = "userName", defaultValue = "root") String userName,
                               @RequestParam(value = "password", defaultValue = "Baili888.") String password,
                               @RequestParam(value = "sqlScriptPath", defaultValue = "psth")
                               String sqlScriptPath) {
        String mysqlUrl = url +
                "?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true" +
                "&allowPublicKeyRetrieval=true";
        if (dataInitializationService.runSqlScript(mysqlUrl, userName, password, sqlScriptPath)) {
            logger.info("执行sql成功");
        }
        return "查询慢sql执行成功";
    }

    /**
     * @return String
     * @author baili
     * @date 2022/11/4 10:41
     * 查询表是否存在时要指定库
     */
    @ApiOperation(value = "检查数据库表是否存在")
    @GetMapping("checkTableExists")
    public String checkTableExists(@RequestParam(value = "url",
            defaultValue = "jdbc:mysql://10.10.202.11:3306/xskyTest") String url,
                                   @RequestParam(value = "userName", defaultValue = "root") String userName,
                                   @RequestParam(value = "password", defaultValue = "Baili888.") String password,
                                   @RequestParam(value = "tableName", defaultValue = "xskyTest")
                                   String tableName) {
        String mysqlUrl = url +
                "?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true" +
                "&allowPublicKeyRetrieval=true";
        if (dataInitializationService.checkTableExists(mysqlUrl, userName, password, tableName)) {
            logger.info("表:" + tableName + "存在");
            return "表:" + tableName + "存在";
        }
        return "表:" + tableName + "不存在";
    }
}
