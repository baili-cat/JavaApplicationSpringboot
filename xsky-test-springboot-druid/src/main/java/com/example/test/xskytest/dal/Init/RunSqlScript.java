package com.example.test.xskytest.dal.Init;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author baili
 * @date 2022年11月03日15:30
 */
public class RunSqlScript {
    private static String APPENDED_DB_INFO
            = "?useUnicode=true&characterEncoding=utf-8&useSSL=false&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
    private static String className = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://10.10.202.11:3306/" + APPENDED_DB_INFO;
    private static String username = "root";
    private static String password = "Baili888.";
    private static Connection connection = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
        Class.forName(className);
        connection = DriverManager.getConnection(url, username, password);
        ScriptRunner scriptRunner = new ScriptRunner(connection);
        Resources.setCharset(Charset.forName("UTF8"));
//		scriptRunner.setLogWriter(null);
        scriptRunner.runScript(new FileReader(ResourceUtils.getFile("/Users/baili/git/xsky-test-springboot/xsky-test" +
                "-springboot-dal/src/main/resources/sqls/Init.sql")));
        scriptRunner.closeConnection();
        connection.close();
    }
}
