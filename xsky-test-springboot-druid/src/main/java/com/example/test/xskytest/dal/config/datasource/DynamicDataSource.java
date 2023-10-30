package com.example.test.xskytest.dal.config.datasource;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * 动态数据源（需要继承AbstractRoutingDataSource）
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DsContextHolder.getDatabaseType();
    }
}
