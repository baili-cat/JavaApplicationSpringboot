//package com.example.test.xskytest.dal.config;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.example.test.xskytest.dal.config.datasource.DatabaseTypeEnum;
//import com.example.test.xskytest.dal.config.datasource.DynamicDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//
///**
// * springboot集成mybatis的基本入口 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)
// * 2）创建SqlSessionFactory 3）配置事务管理器，除非需要使用事务，否则不用配置
// */
//@Slf4j
//@Configuration // 该注解类似于spring配置文件
//public class MyBatisConfig {
//
//    @Value("${mysql.db.driverClassName}")
//    private String driverClassName;
//
//    @Value("${mysql.db.url}")
//    private String url;
//
//    @Value("${mysql.db.username}")
//    private String username;
//
//
//    @Value("${mysql.db.password}")
//    private String password;
//
//    @Value("${mysql.druid.initialSize}")
//    private String initialSize;
//
//    @Value("${mysql.druid.minIdle}")
//    private String minIdle;
//
//    @Value("${mysql.druid.maxActive}")
//    private String maxActive;
//
//    @Value("${mysql.druid.maxWait}")
//    private String maxWait;
//
//    @Value("${mysql.druid.timeBetweenEvictionRunsMillis}")
//    private String timeBetweenEvictionRunsMillis;
//
//    @Value("${mysql.druid.minEvictableIdleTimeMillis}")
//    private String minEvictableIdleTimeMillis;
//
//
//    @Bean
//    @ConfigurationProperties(prefix = "mybatis.configuration")
//    public org.apache.ibatis.session.Configuration configuration() {
//        return new org.apache.ibatis.session.Configuration();
//    }
//
//
//    /**
//     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
//     * 可以创建多个，这里默认一个，后续扩展可用
//     * Bean中不设置name默认未数据源名：
//     */
//    @Bean
//    @Primary
//    public DataSource xskyTestDbDataSource() throws Exception {
//        Properties props = new Properties();
//        props.put("driverClassName", driverClassName);
//        props.put("url", url);
//        // props.put("url", "jdbc:mysql://192.168.111.153:3306/tocean");
//        props.put("username", username);
//        props.put("password", password);
//        props.put("initialSize", initialSize);
//        props.put("minIdle", minIdle);
//        props.put("maxActive", maxActive);
//        props.put("maxWait", maxWait);
//        props.put("timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis);
//        props.put("minEvictableIdleTimeMillis", minEvictableIdleTimeMillis);
//        return DruidDataSourceFactory.createDataSource(props);
//    }
//
//
//    /**
//     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
//     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
//     */
//    @Bean
//    public DynamicDataSource dataSource(@Qualifier("xskyTestDbDataSource") DataSource xskyTestDbDataSource) {
//        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
//        targetDataSources.put(DatabaseTypeEnum.MYSQL, xskyTestDbDataSource);
//
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
//        dataSource.setDefaultTargetDataSource(xskyTestDbDataSource);// 默认的datasource设置为myTestDbDataSource
//
//        return dataSource;
//    }
//
//    /**
//     * 根据数据源创建SqlSessionFactory
//     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds, org.apache.ibatis.session.Configuration
//            configuration) throws Exception {
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
//        fb.setConfiguration(configuration);
//        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//      /*  fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
//        fb.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//
//*/
//        return fb.getObject();
//    }
//
//    /**
//     * 配置事务管理器
//     */
//    @Bean
//    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//
//}
//
