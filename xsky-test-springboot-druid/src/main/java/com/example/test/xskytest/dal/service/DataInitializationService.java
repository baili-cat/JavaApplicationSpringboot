package com.example.test.xskytest.dal.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author baili
 * @date 2022年11月04日17:24
 */
@Slf4j
@Service
public class DataInitializationService {
    /*
     *
     * @author baili
     * @date 2022/11/4 17:33
     * @param null
     * @return null
     */
    public Connection jdbcConnection(String mylUrl, String username, String password) throws SQLException {
        try {
            return DriverManager.getConnection(mylUrl, username, password);
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }

    public boolean runSqlScript(String mylUrl, String username, String password, String sqlScriptPath) {
        try {
            Connection connection = jdbcConnection(mylUrl, username, password);
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            Resources.setCharset(StandardCharsets.UTF_8);
            scriptRunner.runScript(new FileReader(ResourceUtils.getFile(sqlScriptPath)));
            scriptRunner.closeConnection();
            connection.close();
            return true;
        } catch (FileNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean runSqlCmd(String mylUrl, String username, String password, String sqlCmd) {
        try {
            Connection connection = jdbcConnection(mylUrl, username, password);
            Statement statement = connection.createStatement();
            statement.execute(sqlCmd);
            statement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkTableExists(String mylUrl, String username, String password, String tableName) {
        try {
            Connection connection = jdbcConnection(mylUrl, username, password);
            String type[] = {"TABLE"};
            return connection.getMetaData().getTables(null, null, tableName, type).next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
