package com.example.test.xskytest.dal.config.datasource;


/**
 * 作用：
 * 1、保存一个线程安全的DatabaseType容器
 */
public class DsContextHolder {
    /**
     * 存放当前线程使用的数据源类型信息
     */
    private static final ThreadLocal<DatabaseTypeEnum> contextHolder = new ThreadLocal<DatabaseTypeEnum>();

    private DsContextHolder() throws Exception {
        throw new InstantiationException("无法实例化");
    }
    /**
     * 设置数据源
     */
    public static void setDatabaseType(DatabaseTypeEnum type) {
        contextHolder.set(type);
    }
    /**
     * 获取数据源
     *
     * @return
     */
    public static DatabaseTypeEnum getDatabaseType() {
        return contextHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
