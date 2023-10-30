package com.example.test.xskytest.dal.moduleDO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


@Getter
@Setter
@ToString(callSuper = true)
public class SqlDO implements RowMapper<SqlDO>, Serializable {

    private Long id;

    private String testCaseCode;

    private String testCaseName;

    private String testCaseTxt;

    private String testCaseRemark;

    @Override
    public SqlDO mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
