package com.example.test.xskytest.dal.config.datasource;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface DsSwitcher {
    //默认数据源是mysql
    DatabaseTypeEnum value() default DatabaseTypeEnum.MYSQL;
}
