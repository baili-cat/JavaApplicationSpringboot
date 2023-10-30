package com.example.test.xskytest.controller.HttpMysqlTestCaseController;

import com.example.test.xskytest.dal.moduleDO.SqlDO;
import com.example.test.xskytest.dal.service.MysqlService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author baili
 * @date 2022年10月31日11:20
 */
@RestController
@RequestMapping("/mysql/xSkyTest/")
public class HttpMysql {

    Logger logger = LoggerFactory.getLogger(HttpMysql.class);
    @Resource
    private MysqlService mysqlService;




    /**
     * @return 慢sql
     * @author baili
     * @date 2022/11/4 10:41
     */
    @ApiOperation(value = "查询慢sql，可通过tableName指定表名，sleepTime指定sql查询延迟时间（单位ms）")
    @GetMapping("slowSql")
    public String slowSql(@RequestParam(value = "tableName", defaultValue = "testOps_info") String tableName,
                          @RequestParam(value = "sleepTime", defaultValue = "1") String sleepTime) {
        try {
            //sql查询等待时间：单位s
            mysqlService.slowSql(tableName, (Integer.parseInt(sleepTime)));
            logger.info("查询慢sql" + tableName + "成功" + "延迟设置时间为：" + sleepTime);
            return "查询慢sql执行成功, 延迟设置时间为:" + sleepTime + "秒";
        } catch (Exception e) {
            throw new RuntimeException("查询慢sql" + tableName + "失败");

        }
    }

    /**
     * @return sql异常
     * @author baili
     * @date 2022/11/4 10:41
     */
    @ApiOperation(value = "构造sql查询异常")
    @GetMapping("sqlException")
    public String sqlException(@RequestParam(value = "tableName", defaultValue = "testOps_info") String tableName,
                               @RequestParam(value = "id", defaultValue = "1") String id) {
        try {
            //sql查询等待时间：单位ms
            mysqlService.mysqlException(tableName, id);
            return "sql异常构造成功";
        } catch (Exception e) {
            throw new RuntimeException("sql异常，构造失败");
        }
    }

    /**
     * @return 查询指定表数据
     * @author baili
     * @date 2022/11/4 10:41
     */
    @ApiOperation(value = "查询当前应用启动时配置的数据库中指定表所有表信息，通过tableName指定表名")
    @GetMapping("queryTableInformation")
    public List<SqlDO> queryTableInformation(@RequestParam(value = "tableName", defaultValue = "testOps_info") String tableName) {
        return mysqlService.queryTableInformation(tableName);
    }

    /**
     * @return 清空表
     * @author baili
     * @date 2022/11/4 10:41
     */
    @ApiOperation(value = "删除当前应用启动时配置的数据库中指定表所有表信息，通过tableName指定表名")
    @GetMapping("deleteTableInformation")
    public String deleteTableInformation(@RequestParam(value = "tableName", defaultValue = "testOps_info") String tableName) {
        try {
            mysqlService.deleteTableInformation(tableName);
            logger.info("删除表" + tableName + "成功");
            return "deleteTableInformation执行成功";
        } catch (Exception e) {
            throw new RuntimeException("删除表" + tableName + "失败");
        }
    }


}

