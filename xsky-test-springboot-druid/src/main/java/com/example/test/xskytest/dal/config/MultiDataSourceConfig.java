package com.example.test.xskytest.dal.config;

import com.example.test.xskytest.dal.config.datasource.DatabaseTypeEnum;
import com.example.test.xskytest.dal.config.datasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author baili
 * @date 2023年05月06日10:49
 */
@Configuration
public class MultiDataSourceConfig {
    ////实体类位置
    //@Value("${mybatis.type-aliases-package}")
    //private String typeAliasesPackage;
    ////mapper的位置
    //@Value("${mybatis.mapper-locations}")
    //private String mapperLocations;

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration configuration() {
        return new org.apache.ibatis.session.Configuration();
    }

    /**
     * postgresql数据源
     */
    @Bean(name = "pgDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.pg")
    public DataSource pgDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() throws Exception {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "damengDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.dameng")
    public DataSource damengDataSource() throws Exception {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource multiDataSource(
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource) throws Exception {
        Map<Object, Object> target = new HashMap<>();
        target.put(DatabaseTypeEnum.PG,pgDataSource());
        target.put(DatabaseTypeEnum.MYSQL, mysqlDataSource);
        target.put(DatabaseTypeEnum.DAMENG, damengDataSource());

        AbstractRoutingDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(mysqlDataSource);
        dataSource.setTargetDataSources(target);
        return dataSource;
    }

    ///**
    // * mybatis-plus分页插件
    // */
    //@Bean
    //public PaginationInterceptor paginationInterceptor() {
    //    return new PaginationInterceptor();
    //}
    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("multiDataSource") DataSource multiDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multiDataSource);
//        fb.setConfiguration(configuration);
//        MybatisConfiguration configuration = new MybatisConfiguration();
//        configuration.setJdbcTypeForNull(JdbcType.NULL);
//        configuration.setMapUnderscoreToCamelCase(true);
//        configuration.setCacheEnabled(false);
//        sqlSessionFactory.setConfiguration(configuration);
        //sqlSessionFactory.setPlugins(new Interceptor[]{
        //        paginationInterceptor           //添加分页功能
        //});
        //ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));  //配置mapper位置
        //sqlSessionFactory.setGlobalConfig(globalConfiguration());
        //sqlSessionFactory.setTypeAliasesPackage(typeAliasesPackage);//配置实体类位置
        return sqlSessionFactory.getObject();

    }

    ///**
    // * mybatis-plus中SQL执行效率插件，生产环境可以关闭
    // */
    //@Bean
    //public PerformanceInterceptor performanceInterceptor() {
    //    return new PerformanceInterceptor();
    //}
    //
    //@Bean
    //public GlobalConfiguration globalConfiguration() {
    //    GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
    //    conf.setLogicDeleteValue("-1");
    //    conf.setLogicNotDeleteValue("1");
    //    conf.setIdType(0);
    //    conf.setMetaObjectHandler(new MyMetaObjectHandler());
    //    conf.setDbColumnUnderline(true);
    //    conf.setRefresh(true);
    //    return conf;
    //}
}
