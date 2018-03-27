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


@Configuration
@MapperScan(basePackages = "com.mybatis.usera.mapper",sqlSessionTemplateRef = "aSqlSessionTemplate")
public class ADataSourceConfig {

    @Primary
    @Bean(name = "aDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.a")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "aTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("aDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "aSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("aDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/a/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "aSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("aSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}