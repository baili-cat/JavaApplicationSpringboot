package com.example.test.xskytest.dal.config.datasource;

/**
 * 列出所有的数据源key（常用数据库名称来命名）
 * 注意：
 * 1）这里数据源与数据库是一对一的
 * 2）DatabaseType中的变量名称就是数据库的名称
 */
public enum DatabaseTypeEnum {
    PG("PG"), MYSQL("MYSQL"), DAMENG("DAMENG");

    private String value;

    DatabaseTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
