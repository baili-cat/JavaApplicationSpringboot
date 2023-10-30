package com.example.test.xskytest.dal.dao;

import com.example.test.xskytest.dal.mapper.MysqlDOMapper;
import com.example.test.xskytest.dal.moduleDO.SqlDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2020/8/19.
 */
@Repository
@Component
public class MysqlDODao {

    @Autowired
    MysqlDOMapper mysqlDOMapper;

    /**
     * 测试用例查询某一张表的信息
     */
    public List<SqlDO> queryTableInformation(String tableName) {
        return mysqlDOMapper.selectAll(tableName);
    }

    /**
     * 测试用例删除某一张表的信息
     */
    public void deleteTableInformation(String tableName) {
        mysqlDOMapper.deleteAll(tableName);
    }


    /**
     * 测试用例查询sql返回异常
     */
    public List<SqlDO> mysqlTestException(String tableName, String id) {
        return mysqlDOMapper.mysqlTestException(tableName, id);
    }


    /**
     * 查询执行一条慢sql
     */
    public List<SqlDO> slowSql(String tableName, int sleepTime) {
        return mysqlDOMapper.slowSql(tableName, sleepTime);
    }


}

