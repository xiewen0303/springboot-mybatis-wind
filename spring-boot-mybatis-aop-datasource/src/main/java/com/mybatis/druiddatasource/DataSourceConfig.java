package com.mybatis.druiddatasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@MapperScan(basePackages = "com.mybatis",sqlSessionTemplateRef = "dynamicSqlSessionTemplate")
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.a")
    public DataSource dataSourceMaster() {
        return new DruidDataSource();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.b")
    public DataSource dataSourceSlave() {
        return new DruidDataSource();
    }


    @Bean(name = "transactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "dynamicSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("dynamicSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="dynamicDataSource")
    public DataSource dataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource master = dataSourceMaster();
        DataSource slave = dataSourceSlave();

        //设置数据源
        dynamicDataSource.setDefaultTargetDataSource(master);

        //配置多数据源
        Map<Object,Object> map = new HashMap<>();
        map.put(DataSourceType.Master.getName(),master);
        map.put(DataSourceType.Slave.getName(),slave);
        dynamicDataSource.setTargetDataSources(map);


        return dynamicDataSource;
    }
}