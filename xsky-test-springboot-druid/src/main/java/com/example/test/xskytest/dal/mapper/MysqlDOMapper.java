package com.example.test.xskytest.dal.mapper;


import com.example.test.xskytest.dal.moduleDO.SqlDO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface MysqlDOMapper {

    /*
     *
     * @author baili
     * @date 2022/11/3 18:49
     * @param null
     * @return null
     */
    @Select("select * from ${tableName}")
    //@Results(value = {@Result(id = true, column = "tableName")})
    List<SqlDO> selectAll(@Param("tableName") String tableName);

    /*
     *
     * @author baili
     * @date 2022/11/4 14:06
     * @param  null
     * @return null
     */
    @Delete("delete from ${tableName}")
    @Results(value = {@Result(id = true, column = "tableName")})
    void deleteAll(@Param("tableName") String tableName);

    /*
     *
     * @author baili
     * @date 2022/11/3 18:47
     * @param tableName:需要查询的表名，条件表中的id
     * @return null
     */

    @Select("select * from ${tableName}  where  id = #{id}")
    @Results(value = {@Result(id = true, column = "id", property = "id")})
    List<SqlDO> mysqlTestException(@Param("tableName") String tableName, @Param("id") String id);


    @Select("select sleep(${sleepTime}),count(*) from ${tableName}")
        //@Results(value = {@Result(id = true, column = "sleep(10)", property = "testCaseCode"), @Result(column = "count(*)", property = "testCaseName")})
    List<SqlDO> slowSql(@Param("tableName") String tableName, @Param("sleepTime") int sleepTime);

}
