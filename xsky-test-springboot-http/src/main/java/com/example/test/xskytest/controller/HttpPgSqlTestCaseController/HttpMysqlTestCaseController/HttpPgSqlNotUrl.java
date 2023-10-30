package com.example.test.xskytest.controller.HttpPgSqlTestCaseController.HttpMysqlTestCaseController;

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
@RequestMapping("/pgsql/xSkyTest/")
public class HttpPgSqlNotUrl {
    Logger logger = LoggerFactory.getLogger(HttpPgSqlNotUrl.class);
    @Resource
    private DataInitializationService dataInitializationService;
    /**
     * @return java.util.List<com.example.test.xskytest.dal.moduleDO.TestCaseDO>
     * @author baili
     * @date 2022/11/4 10:41
     */
    @ApiOperation(value = "运行sql脚本，暂时执行当前应用初始化需要的sql，后续可扩展为远程执行sql")
    @GetMapping("runSqlScript")
    public String runSqlScript(@RequestParam(value = "url", defaultValue = "jdbc:postgresql://10.10.227.18:5432/baili") String url,
                               @RequestParam(value = "userName", defaultValue = "postgres") String userName,
                               @RequestParam(value = "password", defaultValue = "Baili888.") String password,
                               @RequestParam(value = "sqlScriptPath", defaultValue = "psth")
                               String sqlScriptPath) {
        String pgsqlUrl = url +
                "?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true" +
                "&allowPublicKeyRetrieval=true";
        if (dataInitializationService.runSqlScript(pgsqlUrl, userName, password, sqlScriptPath)) {
            logger.info("执行sql成功");
        }
        return "查询慢sql执行成功";
    }

    @ApiOperation(value = "")
    @GetMapping("initSql")
    public String initSql(@RequestParam(value = "url", defaultValue = "jdbc:postgresql://10.10.227.18:5432/baili") String url,
                          @RequestParam(value = "userName", defaultValue = "postgres") String userName,
                          @RequestParam(value = "password", defaultValue = "Baili888.") String password) {
        try {
            //建表语句
            String initSql = "CREATE DATABASE IF NOT EXISTS testOps_test DEFAULT CHARACTER SET utf8mb4;\n" +
                    "USE testOps_test;\n" +
                    "CREATE TABLE  IF NOT EXISTS testOps_info\n" +
                    "(\n" +
                    "    `id`                BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                    "    `userName`         varchar(60)         NOT NULL COMMENT '用户名',\n" +
                    "    `userAge`       varchar(30)         NOT NULL COMMENT '用户年龄',\n" +
                    "    `node_name`         varchar(60)         NOT NULL COMMENT '所在节点名称',\n" +
                    "    PRIMARY KEY (`id`),\n" +
                    "    UNIQUE KEY `unique_client_id` (`userName`),\n" +
                    "    KEY `idx_node_name` (`node_name`)\n" +
                    ") ENGINE = InnoDB\n" +
                    "  DEFAULT CHARSET = utf8mb4 COMMENT ='客户端信息';\n" +
                    "INSERT INTO testOps_info (`userName`, `userAge`, `node_name`)\n" +
                    "VALUES ('TestOps', '1', 'demo');";
            String mysqlUrl = "jdbc:mysql://" + url +
                    "/?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true" +
                    "&allowPublicKeyRetrieval=true&allowMultiQueries=true";
            //开启慢sql语句
            String sowlSql = "set global slow_query_log='ON';\n" +
                    "set global long_query_time=1;";
            //sql查询等待时间：单位ms
            dataInitializationService.runSqlCmd(mysqlUrl, userName, password, initSql);
            dataInitializationService.runSqlCmd(mysqlUrl, userName, password, sowlSql);

            logger.info("初始化sql命令执行完成,创建DATABASE：testOps_test，table:testOps_info");
            return "初始化sql命令执行完成,已创建DATABASE：testOps_test，table:testOps_info";
        } catch (Exception e) {
            throw new RuntimeException("初始化sql命令执行失败,异常为:", e);

        }
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
            defaultValue = "jdbc:postgresql://10.10.227.18:5432/baili") String url,
                                   @RequestParam(value = "userName", defaultValue = "postgres") String userName,
                                   @RequestParam(value = "password", defaultValue = "Baili888.") String password,
                                   @RequestParam(value = "tableName", defaultValue = "testOps_info")
                                   String tableName) {
        String pgsqlUrl = url +
                "?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true" +
                "&allowPublicKeyRetrieval=true";
        if (dataInitializationService.checkTableExists(pgsqlUrl, userName, password, tableName)) {
            logger.info("表:" + tableName + "存在");
            return "表:" + tableName + "存在";
        }
        return "表:" + tableName + "不存在";
    }
}
