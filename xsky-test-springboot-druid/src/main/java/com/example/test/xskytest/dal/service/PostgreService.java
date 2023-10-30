package com.example.test.xskytest.dal.service;


import com.example.test.xskytest.dal.config.datasource.DatabaseTypeEnum;
import com.example.test.xskytest.dal.config.datasource.DsSwitcher;
import com.example.test.xskytest.dal.dao.PostgreDODao;
import com.example.test.xskytest.dal.moduleDO.SqlDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author admin
 * @date 2020/8/20
 */
@Slf4j
@Service
public class PostgreService {

    @Resource
    PostgreDODao postgreDODao;

    /**
     * 测试用例查询某一张表的信息
     */
    @DsSwitcher(DatabaseTypeEnum.PG)
    public List<SqlDO> queryTableInformation(String tableName) {
        return postgreDODao.queryTableInformation(tableName);
    }

    /**
     * 测试用例删除某一张表的信息
     */

    @DsSwitcher(DatabaseTypeEnum.PG)
    public void deleteTableInformation(String tableName) {
        postgreDODao.deleteTableInformation(tableName);
    }

    /**
     * 通过构造查询异常
     *
     * @return TestCaseDO   表数据DO对象
     */

    @DsSwitcher(DatabaseTypeEnum.PG)
    public List<SqlDO> mysqlException(String tableName, String id) {

        return postgreDODao.postgreSqlTestException(tableName, id);

    }


    /**
     * 执行一条慢sql模拟超时
     * sleepTime不能为空,单位微妙
     *
     * @return TestCaseDO   表数据DO对象
     */
    @DsSwitcher(DatabaseTypeEnum.PG)
    public List<SqlDO> slowSql(String tableName, int sleepTime) {

        return postgreDODao.slowSql(tableName, sleepTime);

    }

}
